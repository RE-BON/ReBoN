package com.handong.rebon.shop.presentation.dto.response.tag;

import com.handong.rebon.shop.application.dto.response.tag.ShopTagResponseDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShopTagResponse {
    private Long id;
    private String name;

    public ShopTagResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ShopTagResponse from(ShopTagResponseDto dto) {
        return new ShopTagResponse(dto.getId(), dto.getName());
    }
}
