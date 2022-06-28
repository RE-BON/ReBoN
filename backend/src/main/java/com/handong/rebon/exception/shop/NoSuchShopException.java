package com.handong.rebon.exception.shop;

import org.springframework.http.HttpStatus;

public class NoSuchShopException extends ShopException {

    public NoSuchShopException() {
        super("존재하지 않는 가게입니다.", HttpStatus.BAD_REQUEST);
    }
}
