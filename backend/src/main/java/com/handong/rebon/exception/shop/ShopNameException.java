package com.handong.rebon.exception.shop;

import org.springframework.http.HttpStatus;

public class ShopNameException extends ShopException {

    public ShopNameException() {
        super("빈 문자열이 올 수 없습니다.", HttpStatus.BAD_REQUEST);
    }
}
