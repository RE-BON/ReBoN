package com.handong.rebon.shop.domain.location;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
