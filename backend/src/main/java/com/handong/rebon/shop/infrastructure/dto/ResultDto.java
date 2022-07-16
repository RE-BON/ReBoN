package com.handong.rebon.shop.infrastructure.dto;

import java.util.List;

import com.handong.rebon.category.domain.Category;

public class ResultDto {
    private PlaceDto place;

    public ResultDto() {
    }

    public ResultDto(PlaceDto place) {
        this.place = place;
    }

    public List<ShopInfoDto> getAllShops() {
        return place.getList();
    }

    public PlaceDto getPlace() {
        return place;
    }

    public void setCategory(Category category) {
        this.place.setCategory(category);
    }
}
