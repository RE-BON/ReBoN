package com.handong.rebon.acceptance.auth;

import java.util.Map;

import com.handong.rebon.acceptance.AcceptanceTest;
import com.handong.rebon.auth.domain.GoogleUserInfo;
import com.handong.rebon.auth.domain.OauthProvider;
import com.handong.rebon.auth.infrastructure.GoogleRequester;
import com.handong.rebon.member.presentation.dto.request.MemberCreateRequest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class AuthAcceptanceTest extends AcceptanceTest {

    @MockBean
    GoogleRequester googleRequester;

    @Test
    @DisplayName("Google 로그인으로 요청이 오면 토큰을 발급한다.")
    void loginByGoogleOauth() {
        //given
        OauthProvider google = OauthProvider.GOOGLE;
        String testEmail = "test@gmail.com";
        String testNickname = "test";
        saveMember(new MemberCreateRequest(testEmail, testNickname, "google", true));
        String authorizationCode = "test-code";

        given(googleRequester.supports(google))
                .willReturn(true);
        given(googleRequester.getUserInfoByCode(anyString()))
                .willReturn(GoogleUserInfo.from(Map.of("email", testEmail)));

        //when
        ExtractableResponse response = login(google.name(), authorizationCode);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.header("Authorization")).isNotNull();
    }

    @Test
    @DisplayName("미가입 로그인 요청시 404에러와 email을 반환 받는다")
    void loginByNotRegisteredMemberGetNotFound() {
        //given
        OauthProvider google = OauthProvider.GOOGLE;
        String testEmail = "test@gmail.com";
        String authorizationCode = "test-code";

        given(googleRequester.supports(google))
                .willReturn(true);
        given(googleRequester.getUserInfoByCode(anyString()))
                .willReturn(GoogleUserInfo.from(Map.of("email", testEmail)));

        //when
        ExtractableResponse response = login(google.name(), authorizationCode);
        Map responseBody = response.body().as(Map.class);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(responseBody.get("email")).isEqualTo(testEmail);
    }

    private ExtractableResponse login(String oauthProvider, String code) {
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


    private ExtractableResponse<Response> saveMember(MemberCreateRequest memberCreateRequest){
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
