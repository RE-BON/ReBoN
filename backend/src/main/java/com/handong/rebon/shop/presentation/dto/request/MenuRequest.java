package com.handong.rebon.shop.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuRequest {
    private String name;
    private int price;

    public MenuRequest(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
