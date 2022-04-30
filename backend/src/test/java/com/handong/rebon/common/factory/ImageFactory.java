package com.handong.rebon.common.factory;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import org.apache.http.entity.ContentType;

public class ImageFactory {

    private ImageFactory() {
    }

    public static MultipartFile createFakeImage(String name) {
        return new MockMultipartFile(
                name,
                name,
                ContentType.IMAGE_JPEG.getMimeType(),
                new byte[]{}
        );
    }
}
