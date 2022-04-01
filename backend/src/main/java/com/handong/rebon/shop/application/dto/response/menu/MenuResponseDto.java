package com.handong.rebon.shop.application.dto.response.menu;

import com.handong.rebon.shop.domain.menu.Menu;

import lombok.Getter;

@Getter
public class MenuResponseDto {
    private Long id;
    private String name;
    private int price;

    public MenuResponseDto(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static MenuResponseDto from(Menu menu) {
        return new MenuResponseDto(menu.getId(), menu.getName(), menu.getPrice());
    }
}
