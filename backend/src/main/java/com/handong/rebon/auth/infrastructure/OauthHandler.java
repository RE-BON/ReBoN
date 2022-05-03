package com.handong.rebon.auth.infrastructure;

import java.util.Map;

import com.handong.rebon.auth.domain.OauthAttributes;
import com.handong.rebon.auth.domain.OauthProvider;
import com.handong.rebon.auth.domain.OauthUserInfo;
import com.handong.rebon.exception.oauth.UnsupportedOauthProviderException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OauthHandler {
    private final Map<String, OauthProvider> oauthProviders;
    private final ApiRequester apiRequester;

    public OauthUserInfo getUserInfoFromCode(String oauthProvider, String code) {
        OauthProvider oauth = getOauthProvider(oauthProvider);
        Map<String, Object> attributes = apiRequester.getUserInfo(code, oauth);
        return OauthAttributes.extract(oauthProvider, attributes);
    }

    private OauthProvider getOauthProvider(String oauthProvider) {
        if (!oauthProviders.containsKey(oauthProvider)) {
            throw new UnsupportedOauthProviderException();
        }
        return oauthProviders.get(oauthProvider);
    }
}
