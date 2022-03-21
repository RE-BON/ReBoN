package com.handong.rebon.member.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Location {
    private String address;
    private String longitude;
    private String latitude;
}
