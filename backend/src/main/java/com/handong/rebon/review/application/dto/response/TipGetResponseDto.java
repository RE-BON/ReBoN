package com.handong.rebon.review.application.dto.response;

import com.handong.rebon.review.domain.Review;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TipGetResponseDto {
    private Long id;
    private String authorName;
    private String shopName;
    private String tip;

//    public static TipGetResponseDto from(Review review) {
//        return new TipGetResponseDto(review.getId(),review.getAuthorName(), review.getShopName(),review.getTip());
//    }
}
