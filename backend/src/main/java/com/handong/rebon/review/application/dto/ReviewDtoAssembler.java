package com.handong.rebon.review.application.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.review.application.dto.response.AdminReviewResponseDto;
import com.handong.rebon.review.application.dto.response.ReviewGetByMemberResponseDto;
import com.handong.rebon.review.application.dto.response.ReviewGetByShopResponseDto;
import com.handong.rebon.review.application.dto.response.TipGetByShopResponseDto;
import com.handong.rebon.review.domain.Review;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewDtoAssembler {

    public static List<ReviewGetByShopResponseDto> reviewGetByShopResponseDtos(List<Review> reviews, Member member) {
        return reviews.stream()
                      .map(review -> reviewGetByShopResponseDto(review, member))
                      .collect(Collectors.toList());
    }

    public static ReviewGetByShopResponseDto reviewGetByShopResponseDto(Review review, Member member) {
        return ReviewGetByShopResponseDto.builder()
                                         .id(review.getId())
                                         .authorName(review.getAuthorName())
                                         .shopName(review.getShopName())
                                         .content(review.getContent())
                                         .tip(review.getTip())
                                         .star(review.getStar())
                                         .empathyCount(review.getEmpathyCount())
                                         .images(review.getImageUrls())
                                         .isLiked(review.isMemberLiked(member))
                                         .build();
    }

    public static List<ReviewGetByMemberResponseDto> reviewGetByMemberResponseDtos(List<Review> reviews) {
        return reviews.stream()
                      .map(ReviewDtoAssembler::reviewGetByMemberResponseDto)
                      .collect(Collectors.toList());
    }

    public static ReviewGetByMemberResponseDto reviewGetByMemberResponseDto(Review review) {
        return ReviewGetByMemberResponseDto.builder()
                                           .id(review.getId())
                                           .shopName(review.getShopName())
                                           .content(review.getContent())
                                           .tip(review.getTip())
                                           .star(review.getStar())
                                           .images(review.getImageUrls())
                                           .createdDate(review.getCreatedAt().toLocalDate())
                                           .build();
    }

    public static List<AdminReviewResponseDto> adminReviewResponseDtos(List<Review> reviews) {
        return reviews.stream()
                      .map(ReviewDtoAssembler::adminReviewResponseDto)
                      .collect(Collectors.toList());
    }

    public static AdminReviewResponseDto adminReviewResponseDto(Review review) {
        return AdminReviewResponseDto.builder()
                                     .id(review.getId())
                                     .authorName(review.getAuthorName())
                                     .shopName(review.getShopName())
                                     .content(review.getContent())
                                     .tip(review.getTip())
                                     .star(review.getStar())
                                     .empathyCount(review.getEmpathyCount())
                                     .images(review.getImageUrls())
                                     .build();
    }

    public static List<TipGetByShopResponseDto> tipGetByShopResponseDtos(List<Review> reviews) {
        return reviews.stream()
                      .map(review -> tipGetByShopResponseDto(review))
                      .collect(Collectors.toList());
    }

//    public static TipGetByShopResponseDto tipGetByShopResponseDto(Review review) {
//        return TipGetByShopResponseDto.builder()
//                                      .id(review.getId())
//                                      .authorName(review.getAuthorName())
//                                      .shopName(review.getShopName())
//                                      .tip(review.getTip())
//                                      .build();
//    }

    public static TipGetByShopResponseDto tipGetByShopResponseDto(Review review) {
        return TipGetByShopResponseDto.builder()
                                      .id(review.getId())
                                      .authorName(review.getAuthorName())
                                      .shopName(review.getShopName())
                                      .content(review.getContent())
                                      .tip(review.getTip())
                                      .star(review.getStar())
                                      .empathyCount(review.getEmpathyCount())
                                      .images(review.getImageUrls())
                                      .build();
    }
}