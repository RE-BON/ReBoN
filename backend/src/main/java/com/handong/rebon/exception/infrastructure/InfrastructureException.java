package com.handong.rebon.exception.infrastructure;

import com.handong.rebon.exception.RebonException;

import org.springframework.http.HttpStatus;

public abstract class InfrastructureException extends RebonException {

    protected InfrastructureException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
