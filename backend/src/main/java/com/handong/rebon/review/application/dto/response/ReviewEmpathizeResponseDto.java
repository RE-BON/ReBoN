package com.handong.rebon.review.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEmpathizeResponseDto {
    private int empathizeCount;
    private boolean empathy;
}
