package com.handong.rebon.shop.domain.content;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Embeddable
public class ShopContent {
    private String name;
    private String businessHour;
    private String phone;

    public ShopContent(String name, String businessHour, String phone) {
        this.name = name;
        this.businessHour = businessHour;
        this.phone = phone;
    }
}
