package com.handong.rebon.auth.domain;

import java.util.Collections;
import java.util.Map;

import lombok.Getter;

@Getter
public class GoogleUserInfo implements OauthUserInfo {
    private Map<String, Object> info;

    private GoogleUserInfo(Map<String, Object> info) {
        this.info = Collections.unmodifiableMap(info);
    }

    public static GoogleUserInfo from(Map<String, Object> responseBody) {
        return new GoogleUserInfo(responseBody);
    }

    @Override
    public String getEmail() {
        return (String) info.get("email");
    }
}
