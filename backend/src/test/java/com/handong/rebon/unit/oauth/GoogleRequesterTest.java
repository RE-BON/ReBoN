package com.handong.rebon.unit.oauth;

import java.io.IOException;

import com.handong.rebon.auth.domain.OauthAttributes;
import com.handong.rebon.auth.domain.OauthProvider;
import com.handong.rebon.auth.domain.OauthUserInfo;
import com.handong.rebon.auth.infrastructure.ApiRequester;
import com.handong.rebon.exception.oauth.GetAccessTokenException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import okhttp3.mockwebserver.MockWebServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GoogleRequesterTest extends ApiRequesterTest {

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
    @DisplayName("서버에 요청을 보내 authorization code로부터 유저의 정보를 가져온다.")
    void getUserInfoByCode() throws IOException {
        //given
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
        setUpResponse(mockWebServer, GOOGLE_TOKEN_RESPONSE);
        setUpResponse(mockWebServer, USER_INFO_RESPONSE);

        OauthProvider oauthProvider = makeOauthProvider(mockWebServer);
        ApiRequester apiRequester = new ApiRequester();
        //when
        OauthUserInfo userInfo = OauthAttributes.extract("google", apiRequester.getUserInfo("code", oauthProvider));
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

        OauthProvider oauthProvider = makeOauthProvider(mockWebServer);
        ApiRequester apiRequester = new ApiRequester();

        //when,then
        assertThatThrownBy(() -> apiRequester.getUserInfo("code", oauthProvider))
                .isInstanceOf(GetAccessTokenException.class);
    }
}
