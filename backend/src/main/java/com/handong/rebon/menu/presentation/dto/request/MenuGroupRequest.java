package com.handong.rebon.menu.presentation.dto.request;

import java.util.List;

import lombok.Getter;

@Getter
public class MenuGroupRequest {
    private String name;
    private List<MenuRequest> menus;

    public MenuGroupRequest(String name, List<MenuRequest> menus) {
        this.name = name;
        this.menus = menus;
    }
}
