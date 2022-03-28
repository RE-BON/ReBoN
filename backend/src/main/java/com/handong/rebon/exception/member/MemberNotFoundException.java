package com.handong.rebon.exception.member;

import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends MemberException {
    public MemberNotFoundException() {
        super("해당 멤버가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
    }
}
