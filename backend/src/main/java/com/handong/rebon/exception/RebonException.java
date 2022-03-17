package com.handong.rebon.exception;

import org.springframework.http.HttpStatus;

public abstract class RebonException extends RuntimeException {
    private final HttpStatus httpStatus;

    public RebonException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}