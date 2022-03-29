package com.handong.rebon.exception.tag;

import com.handong.rebon.exception.RebonException;

import org.springframework.http.HttpStatus;

public abstract class TagException extends RebonException {

    protected TagException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
