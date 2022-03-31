package com.handong.rebon.review.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewDeleteRequestDto {
    private Long memberId;
    private Long reviewId;
}
