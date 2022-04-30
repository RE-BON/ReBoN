package com.handong.rebon.auth.domain;

import java.util.Collections;
import java.util.Map;

public class OauthUserInfo {
    private Map<String, Object> info;

    private OauthUserInfo(Map<String, Object> info) {
        this.info = Collections.unmodifiableMap(info);
    }

    public static OauthUserInfo from(Map<String, Object> responseBody) {
        return new OauthUserInfo(responseBody);
    }

    public String getEmail() {
        return (String) info.get("email");
    }
}
