package com.handong.rebon.review.application.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminReviewPostRequestDto {
    private Long id;
    private String title;
    private String content;
    private String tip;
}
