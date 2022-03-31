package com.handong.rebon.shop.presentation.dto.response.menu;

import com.handong.rebon.shop.application.dto.response.menu.MenuResponseDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
