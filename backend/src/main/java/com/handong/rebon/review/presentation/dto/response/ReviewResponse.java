package com.handong.rebon.review.presentation.dto.response;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReviewResponse {
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
