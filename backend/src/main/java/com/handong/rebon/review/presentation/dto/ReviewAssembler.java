package com.handong.rebon.review.presentation.dto;

import com.handong.rebon.review.application.dto.request.ReviewCreateRequestDto;
import com.handong.rebon.review.application.dto.response.ReviewResponseDto;
import com.handong.rebon.review.presentation.dto.request.ReviewRequest;
import com.handong.rebon.review.presentation.dto.response.ReviewResponse;

public class ReviewAssembler {

    public static ReviewResponse reviewResponse(ReviewResponseDto reviewResponseDto) {
        return ReviewResponse.builder()
                             .id(reviewResponseDto.getId())
                             .shopName(reviewResponseDto.getShopName())
                             .authorName(reviewResponseDto.getShopName())
                             .title(reviewResponseDto.getTitle())
                             .content(reviewResponseDto.getContent())
                             .tip(reviewResponseDto.getTip())
                             .star(reviewResponseDto.getStar())
                             .empathyCount(reviewResponseDto.getEmpathyCount())
                             //.images() TODO 추후 구현
                             .build();
    }

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
