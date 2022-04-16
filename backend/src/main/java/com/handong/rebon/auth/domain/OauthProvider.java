package com.handong.rebon.auth.domain;

import java.util.Locale;

public enum OauthProvider {
    GOOGLE, NAVER, KAKAO;

    public boolean isSameAs(OauthProvider oauthProvider) {
        return this.equals(oauthProvider);
    }

    public static OauthProvider ignoreCase(String oauthProvider) {
        return OauthProvider.valueOf(oauthProvider.toUpperCase(Locale.ROOT));
    }
}
