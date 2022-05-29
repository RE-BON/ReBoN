package com.handong.rebon.exception.category;

import org.springframework.http.HttpStatus;

public class CategoryAlreadyDeletedException extends CategoryException{
    public CategoryAlreadyDeletedException() {
        super("이미 삭제된 카테고리입니다.", HttpStatus.BAD_REQUEST);
    }
}
