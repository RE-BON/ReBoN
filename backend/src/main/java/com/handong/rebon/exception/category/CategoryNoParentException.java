package com.handong.rebon.exception.category;

import org.springframework.http.HttpStatus;

public class CategoryNoParentException extends CategoryException {
    public CategoryNoParentException() {
        super("저장하려는 카테고리의 부모 카테고리가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
    }
}
