package com.handong.rebon.shop.infrastructure.dto;

public class NaverShopDto {
    private ResultDto result;

    public NaverShopDto() {
    }

    public NaverShopDto(ResultDto result) {
        this.result = result;
    }

    public ResultDto getResult() {
        return result;
    }
}
