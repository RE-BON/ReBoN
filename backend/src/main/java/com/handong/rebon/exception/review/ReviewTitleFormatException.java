package com.handong.rebon.exception.review;

import org.springframework.http.HttpStatus;

public class ReviewTitleFormatException extends ReviewException {

    public ReviewTitleFormatException() {
        super("잘못된 타이틀입니다.", HttpStatus.BAD_REQUEST);
    }
}
