package com.handong.rebon.exception.authorization;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends AuthorizationException {
    public InvalidTokenException() {
        super("로그인이 필요한 서비스입니다.", HttpStatus.UNAUTHORIZED);
    }
}
