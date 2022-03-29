package com.handong.rebon.exception.shop;

import org.springframework.http.HttpStatus;

public class NoSuchCategoryException extends ShopException {

    // 카테고리 패키지에 둬야할듯?
    public NoSuchCategoryException() {
        super("없는 카테고리입니다.", HttpStatus.BAD_REQUEST);
    }
}
