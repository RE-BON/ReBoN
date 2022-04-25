package com.handong.rebon.common;

import java.util.List;

import com.handong.rebon.shop.domain.content.ShopImage;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploader {
    List<String> saveAll(List<MultipartFile> multipartFiles);

    void removeAll(List<ShopImage> shopImages);
}
