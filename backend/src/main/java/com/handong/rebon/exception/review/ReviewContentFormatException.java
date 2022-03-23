package com.handong.rebon.exception.review;

import org.springframework.http.HttpStatus;

public class ReviewContentFormatException extends ReviewException {

    public ReviewContentFormatException() {
        super("잘못된 리뷰 내용입니다.", HttpStatus.BAD_REQUEST);
    }
}
