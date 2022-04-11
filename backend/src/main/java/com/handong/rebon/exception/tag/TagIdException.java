package com.handong.rebon.exception.tag;

import org.springframework.http.HttpStatus;

public class TagIdException extends TagException {
    public TagIdException() {
        super("존재하지 않는 tag id값을 요청하였습니다.", HttpStatus.BAD_REQUEST);
    }
}
