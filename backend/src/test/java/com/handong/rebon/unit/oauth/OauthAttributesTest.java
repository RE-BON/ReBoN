package com.handong.rebon.unit.oauth;

import java.util.HashMap;
import java.util.Map;

import com.handong.rebon.auth.domain.OauthAttributes;
import com.handong.rebon.auth.domain.OauthUserInfo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OauthAttributesTest {

    private static String email = "test@gmail.com";

    @Test
    @DisplayName("google attributes를 넘겨주면 OauthUserInfo를 반환 받는다.")
    void googleAttributesGetOauthUserInfo() {
        //given
        Map<String, Object> googleAttributes = new HashMap<>();

        googleAttributes.put("email", email);

        //when
        OauthUserInfo oauthUserInfo = OauthAttributes.extract("google", googleAttributes);

        //then
        assertThat(oauthUserInfo.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("naver attributes를 넘겨주면 OauthUserInfo를 반환 받는다.")
    void naverAttributesGetOauthUserInfo() {
        //given
        Map<String, Object> naverAttributes = new HashMap<>();
        Map<String, Object> response = new HashMap<>();

        response.put("email", email);
        naverAttributes.put("response", response);
        //when
        OauthUserInfo oauthUserInfo = OauthAttributes.extract("naver", naverAttributes);

        //then
        assertThat(oauthUserInfo.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("kakao attributes를 넘겨주면 OauthUserInfo를 반환 받는다.")
    void kakaoAttributesGetOauthUserInfo() {
        //given
        Map<String, Object> kakaoAttributes = new HashMap<>();
        Map<String, Object> kakaoAccount = new HashMap<>();

        kakaoAccount.put("email", email);
        kakaoAttributes.put("kakao_account", kakaoAccount);

        //when
        OauthUserInfo oauthUserInfo = OauthAttributes.extract("kakao", kakaoAttributes);

        //then
        assertThat(oauthUserInfo.getEmail()).isEqualTo(email);
    }
}