package com.handong.rebon.acceptance.auth;

import java.util.Map;

import com.handong.rebon.acceptance.AcceptanceTest;
import com.handong.rebon.acceptance.member.MemberCreateAcceptanceTest;
import com.handong.rebon.member.presentation.dto.request.MemberCreateRequest;

import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;

import static com.handong.rebon.acceptance.AcceptanceUtils.getRequestSpecification;
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
                                .withPath("/google/token")
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
                                .withPath("/google/user-info")
                )
                .respond(
                        response()
                                .withHeader(new Header("Content-Type", "application/json"))
                                .withBody("{\"email\": " + " \"" + TEST_EMAIL + "\"}")
                );
        new MockServerClient("localhost", 8888)
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/naver/token")
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
                                .withPath("/naver/user-info")
                )
                .respond(
                        response()
                                .withHeader(new Header("Content-Type", "application/json"))
                                .withBody(
                                        "{ " +
                                                "\"response\": " +
                                                "{\n" +
                                                "    \"id\": \"167302919239299\",\n" +
                                                "    \"email\": \"" + TEST_EMAIL + "\" \n" +
                                                "}" +
                                                "\n}"
                                )
                );
        new MockServerClient("localhost", 8888)
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/kakao/token")
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
                                .withPath("/kakao/user-info")
                )
                .respond(
                        response()
                                .withHeader(new Header("Content-Type", "application/json"))
                                .withBody(
                                        "{\n" +
                                                "    \"id\": \"167302919239299\",\n" +
                                                "    \"kakao_account\":{\n" +
                                                "       \"email\": \"" + TEST_EMAIL + "\" \n } \n" +
                                                "}"
                                )
                );
    }

    @AfterEach
    void shutDown() {
        mockServer.stop();
    }

    @ParameterizedTest
    @ValueSource(strings = {"google", "kakao", "naver"})
    @DisplayName("oauth 로그인으로 요청이 오면 토큰을 발급한다.")
    void loginByOauth(String oauthProvider) {
        //given
        String testNickname = "test";
        MemberCreateAcceptanceTest.saveMember(new MemberCreateRequest(TEST_EMAIL, testNickname, oauthProvider, true));
        String authorizationCode = "test-code";

        //when
        ExtractableResponse<Response> response = login(oauthProvider, authorizationCode);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.body()).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"google", "kakao", "naver"})
    @DisplayName("미가입 로그인 요청시 401에러와 email을 반환 받는다")
    void loginByNotRegisteredMemberGetNotFound(String oauthProvider) {
        //given
        String authorizationCode = "test-code";

        //when
        ExtractableResponse<Response> response = login(oauthProvider, authorizationCode);
        Map<String, Object> responseBody = response.body().as(new TypeRef<>() {});

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
        assertThat(responseBody.get("email")).isEqualTo(TEST_EMAIL);
    }

    @Test
    @DisplayName("지원하지 않는 provider로 요청시 400에러를 반환한다.")
    void loginByNotSupportedProviderGetBadRequest() {
        //given
        String authorizationCode = "test-code";

        //when
        ExtractableResponse<Response> response = login("github", authorizationCode);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    public static ExtractableResponse<Response> login(String oauthProvider, String code) {
        return RestAssured
                .given(getRequestSpecification())
                .log().all()
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .get("/api/auth/" + oauthProvider + "/login/token?code=" + code)
                .then()
                .log().all()
                .extract();
    }
}
