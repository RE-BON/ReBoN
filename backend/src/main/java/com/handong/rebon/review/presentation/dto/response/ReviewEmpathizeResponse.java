package com.handong.rebon.review.presentation.dto.response;

import com.handong.rebon.review.application.dto.response.ReviewEmpathizeResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEmpathizeResponse {
    private int empathizeCount;
    private boolean empathy;

    public static ReviewEmpathizeResponse from(ReviewEmpathizeResponseDto reviewEmpathizeResponseDto) {
        return new ReviewEmpathizeResponse(
                reviewEmpathizeResponseDto.getEmpathizeCount(),
                reviewEmpathizeResponseDto.isEmpathy());
    }
}
