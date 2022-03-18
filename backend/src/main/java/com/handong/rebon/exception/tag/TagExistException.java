package com.handong.rebon.exception.tag;

import org.springframework.http.HttpStatus;

public class TagExistException extends TagException {

    public TagExistException() {
        super("이미 존재합니다.", HttpStatus.BAD_REQUEST);
    }
}
