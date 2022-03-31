package com.handong.rebon.review.application.dto.response;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReviewGetByShopResponseDto {
    private Long id;
    private String authorName;
    private String shopName;
    private String content;
    private String tip;
    private int star;
    private int empathyCount;
    private List<String> images;
    private boolean isLiked;
}
