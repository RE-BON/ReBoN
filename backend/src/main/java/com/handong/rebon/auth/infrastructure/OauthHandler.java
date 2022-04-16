package com.handong.rebon.auth.infrastructure;

import java.util.List;

import com.handong.rebon.auth.domain.OauthProvider;
import com.handong.rebon.auth.domain.OauthUserInfo;
import com.handong.rebon.exception.oauth.UnsupportedOauthProviderException;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OauthHandler {
    private final List<OauthAPIRequester> oauthAPIRequesters;

    public OauthUserInfo getUserInfoFromCode(OauthProvider oauthProvider, String code) {
        OauthAPIRequester requester = getRequester(oauthProvider);
        return requester.getUserInfoByCode(code);
    }

    private OauthAPIRequester getRequester(OauthProvider oauthProvider) {
        return oauthAPIRequesters.stream()
                                 .filter(oauthAPIRequester -> oauthAPIRequester.supports(oauthProvider))
                                 .findFirst()
                                 .orElseThrow(UnsupportedOauthProviderException::new);
    }
}
