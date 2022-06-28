package com.handong.rebon.exception.member;

import org.springframework.http.HttpStatus;

public class NicknameDuplicateException extends MemberException {
    public NicknameDuplicateException() {
        super("nickname이 중복됩니다.", HttpStatus.BAD_REQUEST);
    }
}
