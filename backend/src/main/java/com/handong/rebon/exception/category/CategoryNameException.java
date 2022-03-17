package com.handong.rebon.exception.category;

import org.springframework.http.HttpStatus;

public class CategoryNameException extends CategoryException{
    public CategoryNameException(){
        super("빈문자열이 입력되었습니다.", HttpStatus.BAD_REQUEST);
    }
}
