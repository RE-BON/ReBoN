package com.handong.rebon.shop.presentation.dto.response;

import com.handong.rebon.shop.application.dto.response.ShopLikeResponseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShopLikeResponse {
    private int likeCount;
    private boolean like;

    public ShopLikeResponse(int likeCount, boolean like) {
        this.likeCount = likeCount;
        this.like = like;
    }

    public static ShopLikeResponse from(ShopLikeResponseDto shopLikeResponseDto) {
        return new ShopLikeResponse(shopLikeResponseDto.getLikeCount(), shopLikeResponseDto.isLike());
    }
}
