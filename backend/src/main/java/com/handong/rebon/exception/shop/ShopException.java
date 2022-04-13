package com.handong.rebon.exception.shop;

import com.handong.rebon.exception.RebonException;

import org.springframework.http.HttpStatus;

public abstract class ShopException extends RebonException {

    protected ShopException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
