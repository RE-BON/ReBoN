package com.handong.rebon.auth.application.dto.request;

import java.util.Locale;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginRequestDto {
    private String oauthProvider;
    private String code;

    public String getOauthProvider() {
        return oauthProvider.toLowerCase(Locale.ROOT);
    }

    public String getCode() {
        return code;
    }
}
