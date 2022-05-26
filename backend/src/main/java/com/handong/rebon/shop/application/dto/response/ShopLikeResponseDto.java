package com.handong.rebon.shop.application.dto.response;

import lombok.Getter;

@Getter
public class ShopLikeResponseDto {
    private int likeCount;
    private boolean isLike;

    public ShopLikeResponseDto(int likeCount, boolean isLike) {
        this.likeCount = likeCount;
        this.isLike = isLike;
    }
}
