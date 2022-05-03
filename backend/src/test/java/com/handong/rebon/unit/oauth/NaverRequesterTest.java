package com.handong.rebon.unit.oauth;

import java.io.IOException;

import com.handong.rebon.auth.domain.OauthAttributes;
import com.handong.rebon.auth.domain.OauthProvider;
import com.handong.rebon.auth.domain.OauthUserInfo;
import com.handong.rebon.auth.infrastructure.ApiRequester;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import okhttp3.mockwebserver.MockWebServer;

import static org.assertj.core.api.Assertions.assertThat;

public class NaverRequesterTest extends ApiRequesterTest{

    private static final String NAVER_TOKEN_RESPONSE = "{\n" +
            "    \"access_token\": \"ACCESS_TOKEN\",\n" +
            "    \"expires_in\": \"3599\",\n" +
            "    \"refresh_token\": \"REFRESH_TOKEN\",\n" +
            "    \"token_type\": \"Bearer\"\n" +
            "}";

    private static final String USER_INFO_RESPONSE = "{ \"response\": {\n" +
            "    \"id\": \"167302919239299\",\n" +
            "    \"email\": \"" + TEST_EMAIL + "\" \n" +
            "}\n}";


    @Test
    @DisplayName("서버에 요청을 보내 authorization code로부터 유저의 정보를 가져온다.")
    void getUserInfoByCode() throws IOException {
        //given
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
        setUpResponse(mockWebServer, NAVER_TOKEN_RESPONSE);
        setUpResponse(mockWebServer, USER_INFO_RESPONSE);

        OauthProvider oauthProvider = makeOauthProvider(mockWebServer);
        ApiRequester apiRequester = new ApiRequester();
        //when
        OauthUserInfo userInfo = OauthAttributes.extract("naver",apiRequester.getUserInfo("code", oauthProvider));
        String email = userInfo.getEmail();

        //then
        assertThat(email).isEqualTo(TEST_EMAIL);
        mockWebServer.shutdown();
    }
}
