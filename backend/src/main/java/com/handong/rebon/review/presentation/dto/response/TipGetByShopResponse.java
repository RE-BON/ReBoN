package com.handong.rebon.review.presentation.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.review.application.dto.response.TipGetByShopResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class TipGetByShopResponse {
    private Long id;
    private String authorName;
    private String shopName;
    private String tip;

    public static List<TipGetByShopResponse> convert(List<TipGetByShopResponseDto> responses) {
        return responses.stream()
                        .map(TipGetByShopResponse::from)
                        .collect(Collectors.toList());
    }

    public static TipGetByShopResponse from(TipGetByShopResponseDto response) {
        return TipGetByShopResponse.builder()
                                   .id(response.getId())
                                   .authorName(response.getAuthorName())
                                   .tip(response.getTip())
                                   .build();
    }
}
