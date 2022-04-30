package com.handong.rebon.acceptance.auth;

import java.util.Map;

import com.handong.rebon.acceptance.AcceptanceTest;
import com.handong.rebon.member.presentation.dto.request.MemberCreateRequest;

import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class AuthAcceptanceTest extends AcceptanceTest {

    public static final String TEST_EMAIL = "handong@gmail.com";

    private ClientAndServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer = ClientAndServer.startClientAndServer(8888);

        new MockServerClient("localhost", 8888)
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/token")
                )
                .respond(
                        response()
                                .withHeader(new Header("Content-Type", "application/json"))
                                .withBody("{ \"access_token\": \"test_token\"}")
                );
        new MockServerClient("localhost", 8888)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/user-info")
                )
                .respond(
                        response()
                                .withHeader(new Header("Content-Type", "application/json"))
                                .withBody("{\"email\": " + " \"" + TEST_EMAIL + "\"}")
                );
    }

    @AfterEach
    void shutDown() {
        mockServer.stop();
    }

    @Test
    @DisplayName("Google 로그인으로 요청이 오면 토큰을 발급한다.")
    void loginByGoogleOauth() {
        //given
        String testNickname = "test";
        saveMember(new MemberCreateRequest(TEST_EMAIL, testNickname, "GOOGLE", true));
        String authorizationCode = "test-code";

        //when
        ExtractableResponse<Response> response = login("google", authorizationCode);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.body()).isNotNull();
    }

    @Test
    @DisplayName("미가입 로그인 요청시 401에러와 email을 반환 받는다")
    void loginByNotRegisteredMemberGetNotFound() {
        //given
        String authorizationCode = "test-code";

        //when
        ExtractableResponse<Response> response = login("google", authorizationCode);
        Map<String, Object> responseBody = response.body().as(new TypeRef<>() {});

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
        assertThat(responseBody.get("email")).isEqualTo(TEST_EMAIL);
    }

    private ExtractableResponse<Response> login(String oauthProvider, String code) {
        return RestAssured
                .given(super.spec)
                .log().all()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .get("/api/auth/" + oauthProvider + "/login/token?code=" + code)
                .then()
                .log().all()
                .extract();
    }

    private ExtractableResponse<Response> saveMember(MemberCreateRequest memberCreateRequest) {
        return RestAssured.given(super.spec)
                          .log().all()
                          .contentType(APPLICATION_JSON_VALUE)
                          .body(memberCreateRequest)
                          .when()
                          .post("/api/members")
                          .then()
                          .log().all()
                          .extract();
    }
}
