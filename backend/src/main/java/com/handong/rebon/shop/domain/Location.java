package com.handong.rebon.shop.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Location {
    private String address;
    private String longitude;
    private String latitude;
}
