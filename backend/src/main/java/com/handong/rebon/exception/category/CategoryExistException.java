package com.handong.rebon.exception.category;

import org.springframework.http.HttpStatus;

public class CategoryExistException extends CategoryException {

    public CategoryExistException() {
        super("이미 존재하는 이름의 Category 입니다.", HttpStatus.BAD_REQUEST);
    }
}
