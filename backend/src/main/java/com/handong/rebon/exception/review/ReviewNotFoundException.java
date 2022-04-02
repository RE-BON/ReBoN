package com.handong.rebon.exception.review;

import org.springframework.http.HttpStatus;

public class ReviewNotFoundException extends ReviewException {
    public ReviewNotFoundException() {
        super("해당 리뷰가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
    }
}
