package com.handong.rebon.auth.infrastructure;

import com.handong.rebon.auth.domain.OauthProvider;
import com.handong.rebon.auth.domain.OauthUserInfo;

public interface OauthAPIRequester {
    boolean supports(OauthProvider oauthProvider);

    OauthUserInfo getUserInfoByCode(String code);
}
