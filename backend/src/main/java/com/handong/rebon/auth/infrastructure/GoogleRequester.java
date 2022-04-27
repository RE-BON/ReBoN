package com.handong.rebon.auth.infrastructure;

import java.util.Map;

import com.handong.rebon.auth.domain.GoogleUserInfo;
import com.handong.rebon.auth.domain.OauthProvider;
import com.handong.rebon.auth.domain.OauthUserInfo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Component
public class GoogleRequester implements OauthAPIRequester {

    @Value("${oauth2.user.google.client-id}")
    private String clientId;

    @Value("${oauth2.user.google.client-secret}")
    private String secretId;

    @Value("${oauth2.user.google.redirect-uri}")
    private String redirectUri;

    @Value("${oauth2.provider.google.token-uri}")
    private String tokenUri;

    @Value("${oauth2.provider.google.user-info-uri}")
    private String userInfoUri;

    @Override
    public boolean supports(OauthProvider oauthProvider) {
        return oauthProvider.isSameAs(OauthProvider.GOOGLE);
    }

    @Override
    public OauthUserInfo getUserInfoByCode(String code) {
        Map<String, Object> userInfo = ApiRequester.getUserInfo(code, tokenUri, clientId, secretId, redirectUri, userInfoUri);
        return GoogleUserInfo.from(userInfo);
    }

}
