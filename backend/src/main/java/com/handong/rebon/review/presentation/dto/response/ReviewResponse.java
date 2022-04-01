package com.handong.rebon.review.presentation.dto.response;

import java.util.List;

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
    private String content;
    private String tip;
    private int star;
    private int empathyCount;
    private List<String> images;
    private boolean isLiked;
}
