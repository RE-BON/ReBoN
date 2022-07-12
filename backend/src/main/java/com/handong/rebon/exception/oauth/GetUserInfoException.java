package com.handong.rebon.exception.oauth;

import org.springframework.http.HttpStatus;

public class GetUserInfoException extends OauthException {
    public GetUserInfoException() {
        super("소셜 로그인에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
