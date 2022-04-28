package com.handong.rebon.shop.presentation.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuGroupRequest {
    private String name;
    private List<MenuRequest> menus = new ArrayList<>();

    public MenuGroupRequest(int menuSize) {
        for (int i = 0; i < menuSize; i++) {
            menus.add(new MenuRequest());
        }
    }

    public MenuGroupRequest(String name, List<MenuRequest> menus) {
        this.name = name;
        this.menus = menus;
    }
}
