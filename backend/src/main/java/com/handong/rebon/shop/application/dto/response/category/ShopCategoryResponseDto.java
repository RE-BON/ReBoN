package com.handong.rebon.shop.application.dto.response.category;

import com.handong.rebon.category.domain.Category;

import lombok.Getter;

@Getter
public class ShopCategoryResponseDto {
    private Long id;
    private String name;

    public ShopCategoryResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ShopCategoryResponseDto from(Category category) {
        return new ShopCategoryResponseDto(category.getId(), category.getName());
    }
}
