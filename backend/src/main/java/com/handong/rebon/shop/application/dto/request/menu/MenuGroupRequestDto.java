package com.handong.rebon.shop.application.dto.request.menu;

import java.util.List;

import lombok.Getter;

@Getter
public class MenuGroupRequestDto {
    private String name;
    private List<MenuRequestDto> menus;

    public MenuGroupRequestDto(String name, List<MenuRequestDto> menus) {
        this.name = name;
        this.menus = menus;
    }
}
