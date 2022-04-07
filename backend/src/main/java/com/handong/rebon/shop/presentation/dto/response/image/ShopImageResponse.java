package com.handong.rebon.shop.presentation.dto.response.image;

import com.handong.rebon.shop.application.dto.response.image.ShopImageResponseDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
