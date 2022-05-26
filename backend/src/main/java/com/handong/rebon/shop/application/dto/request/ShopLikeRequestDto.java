package com.handong.rebon.shop.application.dto.request;

import lombok.Getter;

@Getter
public class ShopLikeRequestDto {
    private Long userId;
    private Long shopId;

    public ShopLikeRequestDto(Long userId, Long shopId) {
        this.userId = userId;
        this.shopId = shopId;
    }
}
