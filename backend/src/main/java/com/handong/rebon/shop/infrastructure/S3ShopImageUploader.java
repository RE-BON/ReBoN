package com.handong.rebon.shop.infrastructure;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.handong.rebon.common.ImageUploader;

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
    public List<String> saveAll(List<MultipartFile> multipartFiles) throws IOException {
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
        return String.format("%s-%s", file.getOriginalFilename(), now);
    }
}
