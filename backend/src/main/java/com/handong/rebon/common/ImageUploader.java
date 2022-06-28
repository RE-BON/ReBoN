package com.handong.rebon.common;

import java.util.List;

import com.handong.rebon.shop.domain.content.ShopImages;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploader {
    List<String> saveAll(List<MultipartFile> multipartFiles);

    void removeAll(ShopImages shopImages);
}
