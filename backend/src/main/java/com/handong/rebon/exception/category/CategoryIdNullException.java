package com.handong.rebon.exception.category;

import org.springframework.http.HttpStatus;

public class CategoryIdNullException extends CategoryException {
    public CategoryIdNullException() {super("변경하려는 카테고리 id값이 입력되어야 합니다.", HttpStatus.BAD_REQUEST);}
}
