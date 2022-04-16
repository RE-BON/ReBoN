package com.handong.rebon.unit.oauth;

import java.io.IOException;

import com.handong.rebon.auth.domain.OauthProvider;
import com.handong.rebon.auth.domain.OauthUserInfo;
import com.handong.rebon.auth.infrastructure.GoogleRequester;
import com.handong.rebon.exception.oauth.ErrorResponseToGetAccessTokenException;

import org.springframework.http.MediaType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.apache.http.HttpHeaders;
import org.jetbrains.annotations.NotNull;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GoogleRequesterTest {

    public static final String TEST_EMAIL = "handong@gmail.com";

    private static final String ERROR_RESPONSE = "{\n" +
            "   \"error\": \"BAD_REQUEST\",\n" +
            "   \"description\": \"Authorization code is invaild\"\n" +
            "}";

    private static final String GOOGLE_TOKEN_RESPONSE = "{\n" +
            "    \"access_token\": \"ACCESS_TOKEN\",\n" +
            "    \"expires_in\": \"3599\",\n" +
            "    \"refresh_token\": \"REFRESH_TOKEN\",\n" +
            "    \"scope\": \"https://www.googleapis.com/auth/drive.metadata.readonly openid https://www.googleapis.com/auth/userinfo.email\",\n" +
            "    \"token_type\": \"Bearer\"\n" +
            "}";

    private static final String USER_INFO_RESPONSE = "{\n" +
            "    \"sub\": \"167302919239299\",\n" +
            "    \"email\": \"" + TEST_EMAIL + "\",\n" +
            "    \"verified_email\": true,\n" +
            "    \"locale\": \"ko\"\n" +
            "}";

    @Test
    @DisplayName("들어온 oauth 제공자가 자신이면 true를 반환한다.")
    void supports() {
        //given
        GoogleRequester googleRequester = new GoogleRequester();

        //when, then
        assertThat(googleRequester.supports(OauthProvider.GOOGLE)).isTrue();
        assertThat(googleRequester.supports(OauthProvider.NAVER)).isFalse();
    }

    @Test
    @DisplayName("서버에 요청을 보내 authorization code로부터 유저의 정보를 가져온다.")
    void getUserInfoByCode() throws IOException {
        //given
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
        setUpResponse(mockWebServer, GOOGLE_TOKEN_RESPONSE);
        setUpResponse(mockWebServer, USER_INFO_RESPONSE);

        GoogleRequester googleRequester = makeGoogleRequester(mockWebServer);

        //when
        OauthUserInfo userInfo = googleRequester.getUserInfoByCode("code");
        String email = userInfo.getEmail();

        //then
        assertThat(email).isEqualTo(TEST_EMAIL);
        mockWebServer.shutdown();

    }

    @Test
    @DisplayName("access token이 오지 않는다면, Exception이 일어난다.")
    void getTokenException() throws IOException {
        //given
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
        setUpResponse(mockWebServer, ERROR_RESPONSE);

        GoogleRequester googleRequester = makeGoogleRequester(mockWebServer);

        //when,then
        assertThatThrownBy(() -> googleRequester.getUserInfoByCode("code"))
                .isInstanceOf(ErrorResponseToGetAccessTokenException.class);


    }

    @NotNull
    private GoogleRequester makeGoogleRequester(MockWebServer mockWebServer) {
        return new GoogleRequester(
                "clientId",
                "secretId",
                "redirectUri",
                String.format("http://%s:%s", mockWebServer.getHostName(), mockWebServer.getPort()),
                String.format("http://%s:%s", mockWebServer.getHostName(), mockWebServer.getPort())
        );
    }

    private void setUpResponse(MockWebServer mockWebServer, String body) {
        mockWebServer.enqueue(new MockResponse()
                .setBody(body)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
    }
}
