package com.handong.rebon.shop.domain.location;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Embeddable
@AllArgsConstructor
public class Location {
    private String address;
    private String longitude;
    private String latitude;

    public Location() {
        this("", "", "");
    }
}
