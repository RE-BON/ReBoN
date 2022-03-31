package com.handong.rebon.exception.member;

import org.springframework.http.HttpStatus;

public class MemberForbiddenException extends MemberException {
    public MemberForbiddenException() {
        super("권한이 없는 멤버입니다.", HttpStatus.FORBIDDEN);
    }
}
