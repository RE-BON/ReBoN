package com.handong.rebon.review.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TipGetByShopResponseDto {
    private Long id;
    private String authorName;
    private String shopName;
    private String tip;
}
