package com.handong.rebon.shop.domain.content;

import javax.persistence.Embeddable;

import com.handong.rebon.exception.shop.ShopNameException;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopContent {
    private String name;
    private String businessHour;
    private String phone;

    @Builder
    public ShopContent(String name, String businessHour, String phone) {
        validatesBlankName(name);
        this.name = name;
        this.businessHour = businessHour;
        this.phone = phone;
    }

    private void validatesBlankName(String name) {
        if (name.isBlank()) {
            throw new ShopNameException();
        }
    }
}
