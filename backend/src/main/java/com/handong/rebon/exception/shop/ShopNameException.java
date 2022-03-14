package com.handong.rebon.exception.shop;

import org.springframework.http.HttpStatus;

public class ShopNameException extends ShopException {

    public ShopNameException() {
        super("잘못된 이름입니다.", HttpStatus.BAD_REQUEST);
    }
}
