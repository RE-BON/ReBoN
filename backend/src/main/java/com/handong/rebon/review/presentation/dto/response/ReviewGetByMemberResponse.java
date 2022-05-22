package com.handong.rebon.review.presentation.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.review.application.dto.response.ReviewGetByMemberResponseDto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReviewGetByMemberResponse {
    private Long id;
    private String shopName;
    private String content;
    private String tip;
    private int star;
    private int empathyCount;
    private List<String> images;

    public static List<ReviewGetByMemberResponse> convert(List<ReviewGetByMemberResponseDto> responses) {
        return responses.stream()
                        .map(ReviewGetByMemberResponse::from)
                        .collect(Collectors.toList());
    }

    public static ReviewGetByMemberResponse from(ReviewGetByMemberResponseDto response) {
        return ReviewGetByMemberResponse.builder()
                                        .id(response.getId())
                                        .shopName(response.getShopName())
                                        .content(response.getContent())
                                        .tip(response.getTip())
                                        .star(response.getStar())
                                        .images(response.getImages())
                                        .build();
    }
}
