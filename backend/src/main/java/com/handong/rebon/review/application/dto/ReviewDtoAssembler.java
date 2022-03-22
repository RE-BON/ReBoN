package com.handong.rebon.review.application.dto;

import com.handong.rebon.review.application.dto.response.ReviewResponseDto;
import com.handong.rebon.review.domain.Review;

public class ReviewDtoAssembler {

    public static ReviewResponseDto reviewResponseDto(Review review) {
        return ReviewResponseDto.builder()
                                .id(review.getId())
                                .shopName(review.getShopName())
                                .authorName(review.getAuthorName())
                                .title(review.getTitle())
                                .content(review.getContent())
                                .tip(review.getTip())
                                .star(review.getStar())
                                .empathyCount(review.getEmpathyCount())
                                //.images() TODO 후에 구현
                                .build();
    }
}
