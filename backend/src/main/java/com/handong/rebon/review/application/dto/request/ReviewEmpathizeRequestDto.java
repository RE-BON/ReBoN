package com.handong.rebon.review.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEmpathizeRequestDto {
    private Long userId;
    private Long reviewId;
}
