package com.handong.rebon.shop.application.dto.response.image;

import com.handong.rebon.shop.domain.content.ShopImage;

import lombok.Getter;

@Getter
public class ShopImageResponseDto {
    private Long id;
    private String url;
    private boolean isMain;

    public ShopImageResponseDto(Long id, String url, boolean isMain) {
        this.id = id;
        this.url = url;
        this.isMain = isMain;
    }

    public static ShopImageResponseDto from(ShopImage shopImage) {
        return new ShopImageResponseDto(shopImage.getId(), shopImage.getUrl(), shopImage.isMain());
    }
}
