package com.handong.rebon.shop.application.dto.request;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ShopRequestDto {
    private Long categoryId;
    private List<Long> tagIds;
    private String name;

    @Builder
    public ShopRequestDto(Long categoryId, List<Long> tagIds, String name) {
        this.categoryId = categoryId;
        this.tagIds = tagIds;
        this.name = name;
    }
}
