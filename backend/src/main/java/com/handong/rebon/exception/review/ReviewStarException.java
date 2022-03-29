package com.handong.rebon.exception.review;

import org.springframework.http.HttpStatus;

public class ReviewStarException extends ReviewException {
    public ReviewStarException() {
        super("별점은 범위를 벗어났습니다.", HttpStatus.BAD_REQUEST);
    }
}
