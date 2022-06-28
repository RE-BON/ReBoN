package com.handong.rebon.exception.infrastructure;

import org.springframework.http.HttpStatus;

public class ImageSaveException extends InfrastructureException {

    public ImageSaveException() {
        super("이미지 저장 서버 에러", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
