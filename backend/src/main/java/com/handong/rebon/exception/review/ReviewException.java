package com.handong.rebon.exception.review;

import com.handong.rebon.exception.RebonException;
import org.springframework.http.HttpStatus;

public abstract class ReviewException extends RebonException {

    protected ReviewException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
