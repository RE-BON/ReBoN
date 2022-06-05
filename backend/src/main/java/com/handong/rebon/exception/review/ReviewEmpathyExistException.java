package com.handong.rebon.exception.review;

import org.springframework.http.HttpStatus;

public class ReviewEmpathyExistException extends ReviewException {
    public ReviewEmpathyExistException() {
        super("이미 공감한 리뷰에 중복해서 공감할 수 없습니다.", HttpStatus.BAD_REQUEST);
    }
}
