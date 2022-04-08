package com.handong.rebon.common.fakebean;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.common.ImageUploader;

import org.springframework.web.multipart.MultipartFile;

public class TestImageUploader implements ImageUploader {

    @Override
    public List<String> saveAll(List<MultipartFile> multipartFiles) {
        return multipartFiles.stream()
                             .map(MultipartFile::getOriginalFilename)
                             .collect(Collectors.toList());
    }
}
