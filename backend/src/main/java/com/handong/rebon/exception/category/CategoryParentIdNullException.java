package com.handong.rebon.exception.category;

import org.springframework.http.HttpStatus;

public class CategoryParentIdNullException extends CategoryException {
    public CategoryParentIdNullException() {
        super("부모 카테고리 id값이 입력되어야 합니다.", HttpStatus.BAD_REQUEST);
    }
}
