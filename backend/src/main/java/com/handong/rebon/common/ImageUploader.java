package com.handong.rebon.common;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploader {
    List<String> saveAll(List<MultipartFile> multipartFiles) throws IOException;
}
