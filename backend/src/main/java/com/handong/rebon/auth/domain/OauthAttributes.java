package com.handong.rebon.auth.domain;

import java.util.Arrays;
import java.util.Map;

public enum OauthAttributes {
    NAVER("naver") {
        @Override
        public OauthUserInfo of(Map<String, Object> attributes) {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
            return OauthUserInfo.builder()
                                .email((String) response.get("email"))
                                .build();
        }
    },
    GOOGLE("google") {
        @Override
        public OauthUserInfo of(Map<String, Object> attributes) {
            return OauthUserInfo.builder()
                                .email((String) attributes.get("email"))
                                .build();
        }
    },
    KAKAO("kakao") {
        @Override
        public OauthUserInfo of(Map<String, Object> attributes) {
            Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
            return OauthUserInfo.builder()
                                .email((String) account.get("email"))
                                .build();
        }
    };

    private final String providerName;

    OauthAttributes(String name) {
        this.providerName = name;
    }

    public static OauthUserInfo extract(String providerName, Map<String, Object> attributes) {
        return Arrays.stream(values())
                     .filter(provider -> providerName.equals(provider.providerName))
                     .findFirst()
                     .orElseThrow(IllegalArgumentException::new)
                     .of(attributes);
    }

    public abstract OauthUserInfo of(Map<String, Object> attributes);
}
