package com.handong.rebon.exception.member;

import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends MemberException {

    public MemberNotFoundException() {
        super("해당 멤버를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
    }
}
