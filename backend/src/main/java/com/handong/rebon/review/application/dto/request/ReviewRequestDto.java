package com.handong.rebon.review.application.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewRequestDto {
    private Long memberId;
    private Long shopId;
}
