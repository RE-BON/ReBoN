package com.handong.rebon.review.application.dto.request;

import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReviewGetByShopRequestDto {
    private Long memberId;
    private Long shopId;
    private Pageable pageable;
}
