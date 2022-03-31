package com.handong.rebon.shop.presentation.dto.response.image;

import com.handong.rebon.shop.application.dto.response.image.ShopImageResponseDto;

public class ShopImageResponse {
    private String url;
    private boolean isMain;

    public ShopImageResponse(String url, boolean isMain) {
        this.url = url;
        this.isMain = isMain;
    }

    public static ShopImageResponse from(ShopImageResponseDto shopImageResponseDto) {
        return new ShopImageResponse(shopImageResponseDto.getUrl(), shopImageResponseDto.isMain());
    }
}
