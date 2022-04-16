package com.handong.rebon.exception.oauth;

import com.handong.rebon.exception.RebonException;

import org.springframework.http.HttpStatus;

public class UnsupportedOauthProviderException extends RebonException {

    public UnsupportedOauthProviderException() {
        super("지원되지 않는 소셜로그인입니다.", HttpStatus.BAD_REQUEST);
    }
}
