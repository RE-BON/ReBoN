package com.handong.rebon.unit.oauth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.handong.rebon.auth.domain.OauthProvider;
import com.handong.rebon.auth.domain.OauthUserInfo;
import com.handong.rebon.auth.infrastructure.OauthHandler;
import com.handong.rebon.exception.oauth.UnsupportedOauthProviderException;

import org.springframework.http.MediaType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.apache.http.HttpHeaders;
import org.jetbrains.annotations.NotNull;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OauthHandlerTest {

    public static final String TEST_EMAIL = "handong@gmail.com";

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
    @DisplayName("올바른 provider가 제공되면, 유저 정보를 얻을 수 있다.")
    void getUserInfoByOauthProvider() throws IOException {
        //given
        MockWebServer mockWebServer = setUpMockServer();

        Map<String, OauthProvider> oauthProviders = setUpProviders(mockWebServer);

        OauthHandler oauthHandler = new OauthHandler(oauthProviders);

        //when
        OauthUserInfo userInfo = oauthHandler.getUserInfoFromCode("google", "code");
        String email = userInfo.getEmail();

        //then
        assertThat(email).isEqualTo(TEST_EMAIL);
        mockWebServer.shutdown();
    }

    @Test
    @DisplayName("지원하지 않는 provider를 받으면 exception을 던진다.")
    void unsupportedProviderException() throws IOException {
        //given
        MockWebServer mockWebServer = setUpMockServer();

        Map<String, OauthProvider> oauthProviders = setUpProviders(mockWebServer);

        OauthHandler oauthHandler = new OauthHandler(oauthProviders);

        //when,then
        assertThatThrownBy(() -> oauthHandler.getUserInfoFromCode("naver", "code"))
                .isInstanceOf(UnsupportedOauthProviderException.class);
    }

    @NotNull
    private Map<String, OauthProvider> setUpProviders(MockWebServer mockWebServer) {
        OauthProvider googleOauthProvider = makeOauthProvider(mockWebServer);

        Map<String, OauthProvider> oauthProviders = new HashMap<>();
        oauthProviders.put("google", googleOauthProvider);
        return oauthProviders;
    }

    @NotNull
    private MockWebServer setUpMockServer() throws IOException {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
        setUpResponse(mockWebServer, GOOGLE_TOKEN_RESPONSE);
        setUpResponse(mockWebServer, USER_INFO_RESPONSE);
        return mockWebServer;
    }

    @NotNull
    private OauthProvider makeOauthProvider(MockWebServer mockWebServer) {
        return new OauthProvider(
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
