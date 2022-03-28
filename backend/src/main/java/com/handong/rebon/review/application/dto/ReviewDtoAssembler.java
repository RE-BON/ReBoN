package com.handong.rebon.review.application.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.review.application.dto.response.ReviewResponseDto;
import com.handong.rebon.review.domain.Review;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewDtoAssembler {

    public static List<ReviewResponseDto> reviewResponseDtos(List<Review> reviews, Member member) {
        return reviews.stream()
                      .map(review -> reviewResponseDto(review, member))
                      .collect(Collectors.toList());
    }

    public static ReviewResponseDto reviewResponseDto(Review review, Member member) {
        return ReviewResponseDto.builder()
                                .id(review.getId())
                                .authorName(review.getAuthorName())
                                .shopName(review.getShopName())
                                .title(review.getTitle())
                                .content(review.getContent())
                                .tip(review.getTip())
                                .star(review.getStar())
                                .empathyCount(review.getEmpathyCount())
                                .images(review.getImageUrls())
                                .isLiked(review.isMemberLiked(member))
                                .build();
    }

    public static List<ReviewResponseDto> reviewResponseDtos(List<Review> reviews) {
        return reviews.stream()
                      .map(review -> reviewResponseDto(review))
                      .collect(Collectors.toList());
    }

    public static ReviewResponseDto reviewResponseDto(Review review) {
        return ReviewResponseDto.builder()
                                .id(review.getId())
                                .authorName(review.getAuthorName())
                                .shopName(review.getShopName())
                                .title(review.getTitle())
                                .content(review.getContent())
                                .tip(review.getTip())
                                .star(review.getStar())
                                .empathyCount(review.getEmpathyCount())
                                .images(review.getImageUrls())
                                .build();
    }
}
