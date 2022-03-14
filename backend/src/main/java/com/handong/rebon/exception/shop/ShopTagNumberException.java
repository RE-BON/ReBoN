package com.handong.rebon.exception.shop;

import org.springframework.http.HttpStatus;

public class ShopTagNumberException extends ShopException {

    public ShopTagNumberException() {
        super("하나 이상의 태그가 필요합니다.", HttpStatus.BAD_REQUEST);
    }
}
