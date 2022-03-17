package com.handong.rebon.exception.tag;

import org.springframework.http.HttpStatus;
import com.handong.rebon.exception.RebonException;

public abstract class TagException extends RebonException {
    protected TagException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}