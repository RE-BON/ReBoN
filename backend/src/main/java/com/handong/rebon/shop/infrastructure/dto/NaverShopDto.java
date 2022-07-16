package com.handong.rebon.shop.infrastructure.dto;

import java.util.List;

import com.handong.rebon.category.domain.Category;

public class NaverShopDto {
    private ResultDto result;

    public NaverShopDto() {
    }

    public NaverShopDto(ResultDto result) {
        this.result = result;
    }

    public List<ShopInfoDto> getAllShops() {
        return result.getAllShops();
    }

    public ResultDto getResult() {
        return result;
    }

    public void setCategory(Category category) {
        this.result.setCategory(category);
    }
}
