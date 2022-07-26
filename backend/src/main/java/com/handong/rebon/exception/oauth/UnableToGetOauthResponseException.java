package com.handong.rebon.exception.oauth;

import org.springframework.http.HttpStatus;

public class UnableToGetOauthResponseException extends OauthException {
    public UnableToGetOauthResponseException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
