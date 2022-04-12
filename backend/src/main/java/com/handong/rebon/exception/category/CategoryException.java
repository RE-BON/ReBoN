package com.handong.rebon.exception.category;

import com.handong.rebon.exception.RebonException;

import org.springframework.http.HttpStatus;

public class CategoryException extends RebonException {
    protected CategoryException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
