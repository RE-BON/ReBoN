package com.handong.rebon.shop.application.dto.request.menu;

import lombok.Getter;

@Getter
public class MenuRequestDto {
    private String name;
    private int price;

    public MenuRequestDto(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
