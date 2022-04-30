package com.handong.rebon.exception.category;

import org.springframework.http.HttpStatus;

public class NoSuchCategoryException extends CategoryException {

    public NoSuchCategoryException() {
        super("존재하지 않는 카테고리입니다.", HttpStatus.BAD_REQUEST);
    }
}
