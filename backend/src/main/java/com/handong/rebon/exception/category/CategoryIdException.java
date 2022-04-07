package com.handong.rebon.exception.category;

import org.springframework.http.HttpStatus;

public class CategoryIdException extends CategoryException {
    public CategoryIdException() {
        super("존재하지 않는 category id값을 요청하였습니다.", HttpStatus.BAD_REQUEST);
    }
}
