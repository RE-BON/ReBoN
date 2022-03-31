package com.handong.rebon.shop.presentation.dto.response.menu;

import com.handong.rebon.shop.application.dto.response.menu.MenuResponseDto;

public class MenuResponse {
    private String name;
    private int price;

    public MenuResponse(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static MenuResponse from(MenuResponseDto menuResponseDto) {
        return new MenuResponse(menuResponseDto.getName(), menuResponseDto.getPrice());
    }
}
