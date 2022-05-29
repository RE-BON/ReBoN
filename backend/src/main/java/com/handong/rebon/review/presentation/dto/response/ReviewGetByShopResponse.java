package com.handong.rebon.review.presentation.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.review.application.dto.response.ReviewGetByShopResponseDto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReviewGetByShopResponse {
    private Long id;
    private String authorName;
    private String shopName;
    private String content;
    private String tip;
    private int star;
    private int empathyCount;
    private List<String> images;
    private boolean liked;

    public static List<ReviewGetByShopResponse> convert(List<ReviewGetByShopResponseDto> responses) {
        return responses.stream()
                        .map(ReviewGetByShopResponse::from)
                        .collect(Collectors.toList());
    }

    public static ReviewGetByShopResponse from(ReviewGetByShopResponseDto response) {
        return ReviewGetByShopResponse.builder()
                                      .id(response.getId())
                                      .authorName(response.getAuthorName())
                                      .shopName(response.getShopName())
                                      .content(response.getContent())
                                      .tip(response.getTip())
                                      .star(response.getStar())
                                      .empathyCount(response.getEmpathyCount())
                                      .images(response.getImages())
                                      .liked(response.isLiked())
                                      .build();
    }
}
