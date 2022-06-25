package com.handong.rebon.member.application.dto.request;

import java.util.Locale;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class MemberReadRequestDto {
    private String email;
    private String oauthProvider;
    private String nickname;
    private boolean agreed;

    public String getOauthProvider() {
        return oauthProvider.toLowerCase(Locale.ROOT);
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isAgreed() {
        return agreed;
    }
}
