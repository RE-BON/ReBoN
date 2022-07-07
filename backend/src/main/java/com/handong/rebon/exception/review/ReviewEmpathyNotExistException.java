package com.handong.rebon.exception.review;

import org.springframework.http.HttpStatus;

public class ReviewEmpathyNotExistException extends ReviewException {
    public ReviewEmpathyNotExistException() {
        super("공감하지 않았던 리뷰에는 공감을 취소할 수 없습니다.", HttpStatus.BAD_REQUEST);
    }
}
