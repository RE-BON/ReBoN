package com.handong.rebon.exception.review;

import org.springframework.http.HttpStatus;

public class ReviewStarException extends ReviewException {
    public ReviewStarException() {
        super("별점은 음수가 될 수 없습니다.", HttpStatus.BAD_REQUEST);
    }
}
