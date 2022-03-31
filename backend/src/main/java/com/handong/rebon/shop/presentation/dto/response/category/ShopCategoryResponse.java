package com.handong.rebon.shop.presentation.dto.response.category;

import com.handong.rebon.shop.application.dto.response.category.ShopCategoryResponseDto;

public class ShopCategoryResponse {
    private Long id;
    private String name;

    public ShopCategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ShopCategoryResponse from(ShopCategoryResponseDto dto) {
        return new ShopCategoryResponse(dto.getId(), dto.getName());
    }
}
