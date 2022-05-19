package com.handong.rebon.exception.authorization;

import com.handong.rebon.exception.RebonException;

import org.springframework.http.HttpStatus;

public abstract class AuthorizationException extends RebonException {

    protected AuthorizationException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
