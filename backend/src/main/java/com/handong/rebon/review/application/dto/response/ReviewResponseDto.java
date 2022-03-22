package com.handong.rebon.review.application.dto.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReviewResponseDto {
    private Long id;
    private String authorName;
    private String shopName;
    private String title;
    private String content;
    private String tip;
    private double star;
    private int empathyCount;
    private List<MultipartFile> images;
}
