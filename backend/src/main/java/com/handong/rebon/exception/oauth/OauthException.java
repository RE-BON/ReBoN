package com.handong.rebon.exception.oauth;

import com.handong.rebon.exception.RebonException;

import org.springframework.http.HttpStatus;

public abstract class OauthException extends RebonException {

    protected OauthException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
