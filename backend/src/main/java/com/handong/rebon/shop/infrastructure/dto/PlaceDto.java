package com.handong.rebon.shop.infrastructure.dto;

import java.util.List;

import com.handong.rebon.category.domain.Category;

public class PlaceDto {
    private List<ShopInfoDto> list;
    private int totalCount;

    public PlaceDto() {
    }

    public PlaceDto(List<ShopInfoDto> list, int totalCount) {
        this.list = list;
        this.totalCount = totalCount;
    }

    public List<ShopInfoDto> getList() {
        return list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setList(List<ShopInfoDto> list) {
        this.list = list;
    }

    public void setBasicData(Category category, String query) {
        list.forEach(dto -> dto.setBasicData(category, query));
    }
}
