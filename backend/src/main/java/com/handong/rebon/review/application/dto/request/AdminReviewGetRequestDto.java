package com.handong.rebon.review.application.dto.request;

import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminReviewGetRequestDto {
    private String keyword;
    private Pageable pageable;
}
