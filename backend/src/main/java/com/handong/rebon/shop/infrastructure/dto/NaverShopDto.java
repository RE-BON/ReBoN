package com.handong.rebon.shop.infrastructure.dto;

import java.util.List;

import com.handong.rebon.category.domain.Category;

public class NaverShopDto {
    private ResultDto result;
    private ErrorDto error;

    public NaverShopDto() {
    }

    public NaverShopDto(ResultDto result, ErrorDto error) {
        this.result = result;
        this.error = error;
    }

    public int getTotalCount() {
        return result.getTotalCount();
    }

    public ResultDto getResult() {
        return result;
    }

    public ErrorDto getError() {
        return error;
    }

    public List<ShopInfoDto> getAllShops() {
        return result.getAllShops();
    }

    public void setBasicData(Category category, String query) {
        this.result.setBasicData(category, query);
    }
}
