package com.handong.rebon.exception.shop;

import org.springframework.http.HttpStatus;

public class ShopNotFoundException extends ShopException{
    public ShopNotFoundException() {
        super("해당 가게가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
    }
}
