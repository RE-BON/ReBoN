package com.handong.rebon.menu.presentation.dto.request;

import lombok.Getter;

@Getter
public class MenuRequest {
    private String name;
    private int price;

    public MenuRequest(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
