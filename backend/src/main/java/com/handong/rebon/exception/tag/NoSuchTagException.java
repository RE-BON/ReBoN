package com.handong.rebon.exception.tag;

import org.springframework.http.HttpStatus;

public class NoSuchTagException extends TagException {

    public NoSuchTagException() {
        super("존재하지 않는 태그입니다", HttpStatus.BAD_REQUEST);
    }
}
