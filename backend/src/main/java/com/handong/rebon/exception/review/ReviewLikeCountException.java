package com.handong.rebon.exception.review;

import org.springframework.http.HttpStatus;

public class ReviewLikeCountException extends ReviewException {

    public ReviewLikeCountException() {
        super("리뷰 공감 숫자는 음수가 될 수 없습니다.", HttpStatus.BAD_REQUEST);
    }
}
