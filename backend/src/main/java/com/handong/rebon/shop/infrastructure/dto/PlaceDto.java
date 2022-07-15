package com.handong.rebon.shop.infrastructure.dto;

import java.util.List;

public class PlaceDto {
    private List<ShopInfoDto> list;

    public PlaceDto() {
    }

    public PlaceDto(List<ShopInfoDto> list) {
        this.list = list;
    }

    public List<ShopInfoDto> getList() {
        return list;
    }

    public void setList(List<ShopInfoDto> list) {
        this.list = list;
    }
}
