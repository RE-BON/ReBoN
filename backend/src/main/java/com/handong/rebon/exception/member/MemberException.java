package com.handong.rebon.exception.member;

import com.handong.rebon.exception.RebonException;

import org.springframework.http.HttpStatus;

public abstract class MemberException extends RebonException {

    protected MemberException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
