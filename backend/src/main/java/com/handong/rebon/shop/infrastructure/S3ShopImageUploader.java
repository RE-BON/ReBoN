package com.handong.rebon.shop.infrastructure;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.handong.rebon.common.ImageUploader;
import com.handong.rebon.exception.infrastructure.ImageSaveException;
import com.handong.rebon.shop.domain.content.ShopImage;
import com.handong.rebon.shop.domain.content.ShopImages;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@Profile("!test")
public class S3ShopImageUploader implements ImageUploader {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public List<String> saveAll(List<MultipartFile> multipartFiles) {
        try {
            return saveEachImage(multipartFiles);
        } catch (IOException e) {
            throw new ImageSaveException();
        }
    }

    @Override
    public void removeAll(ShopImages shopImages) {
        shopImages.getShopImages()
                  .stream()
                  .map(ShopImage::getUrl)
                  .map(url -> url.replace("https://rebon-bucket.s3.ap-northeast-2.amazonaws.com/", ""))
                  .map(url -> new DeleteObjectRequest(bucket, url))
                  .forEach(amazonS3::deleteObject);
    }

    private List<String> saveEachImage(List<MultipartFile> multipartFiles) throws IOException {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            String fileName = getFileName(file);
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            urls.add(amazonS3.getUrl(bucket, fileName).toString());
        }
        return urls;
    }

    private String getFileName(MultipartFile file) {
        String now = LocalDateTime.now()
                                  .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return String.format("%s-%s", now, file.getOriginalFilename());
    }
}
