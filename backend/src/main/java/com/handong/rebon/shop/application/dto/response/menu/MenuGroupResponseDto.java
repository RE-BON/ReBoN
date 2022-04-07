package com.handong.rebon.shop.application.dto.response.menu;

import java.util.List;

import lombok.Getter;

@Getter
public class MenuGroupResponseDto {
    private Long id;
    private String name;
    private List<MenuResponseDto> menus;

    public MenuGroupResponseDto(Long id, String name, List<MenuResponseDto> menus) {
        this.id = id;
        this.name = name;
        this.menus = menus;
    }
}
