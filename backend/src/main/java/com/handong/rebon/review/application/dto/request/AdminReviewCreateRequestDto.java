package com.handong.rebon.review.application.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminReviewCreateRequestDto {
    private String title;
    private String content;
    private String tip;
}
