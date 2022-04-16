package com.handong.rebon.exception.oauth;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class NoSuchOAuthMemberException extends OauthException {

    private final String email;

    public NoSuchOAuthMemberException(String email) {
        super("소셜 로그인 회원이 아닙니다. 회원가입을 진행합니다.", HttpStatus.NOT_FOUND);
        this.email = email;
    }
}
