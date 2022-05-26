package com.handong.rebon.shop.domain.content;

import java.time.LocalTime;
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
    private LocalTime start;
    private LocalTime end;
    private String phone;

    public ShopContent(String name) {
        this(name, LocalTime.MIN, LocalTime.MAX, "");
    }

    @Builder
    public ShopContent(String name, LocalTime start, LocalTime end, String phone) {
        validatesBlankName(name);
        this.name = name;
        this.start = start;
        this.end = end;
        this.phone = phone;
    }

    private void validatesBlankName(String name) {
        if (name.isBlank()) {
            throw new ShopNameException();
        }
    }

    public String getBusinessHour() {
        StringBuilder sb = new StringBuilder();
        sb.append(start).append(":").append(end);
        return sb.toString();
    }
}
