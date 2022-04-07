package com.handong.rebon.exception.tag;

import org.springframework.http.HttpStatus;

public class TagNameException extends TagException {

    public TagNameException() {
        super("잘못된 이름입니다.", HttpStatus.BAD_REQUEST);
    }
}
