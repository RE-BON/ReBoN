package com.handong.rebon.shop.presentation.dto.response.menu;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.shop.application.dto.response.menu.MenuGroupResponseDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuGroupResponse {
    private String name;
    private List<MenuResponse> menus;

    public MenuGroupResponse(String name, List<MenuResponse> menus) {
        this.name = name;
        this.menus = menus;
    }

    public static MenuGroupResponse from(MenuGroupResponseDto dto) {
        List<MenuResponse> menus = dto.getMenus().stream()
                                      .map(MenuResponse::from)
                                      .collect(Collectors.toList());
        return new MenuGroupResponse(dto.getName(), menus);
    }
}
