package com.handong.rebon.review.application.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AdminReviewResponseDto {
    private Long id;
    private String authorName;
    private String shopName;
    private String content;
    private String tip;
    private List<String> images;
    private int star;
    private int empathyCount;
}
