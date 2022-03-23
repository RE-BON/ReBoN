package com.handong.rebon.review.presentation.dto;

import com.handong.rebon.review.application.dto.request.ReviewCreateRequestDto;
import com.handong.rebon.review.presentation.dto.request.ReviewRequest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewAssembler {

    public static ReviewCreateRequestDto reviewCreateRequestDto(Long memberId, Long shopId, ReviewRequest reviewRequest) {
        return ReviewCreateRequestDto.builder()
                                     .memberId(memberId)
                                     .shopId(shopId)
                                     .title(reviewRequest.getTitle())
                                     .content(reviewRequest.getContent())
                                     .tip(reviewRequest.getTip())
                                     .star(reviewRequest.getStar())
                                     .images(reviewRequest.getImages())
                                     .build();

    }
}
