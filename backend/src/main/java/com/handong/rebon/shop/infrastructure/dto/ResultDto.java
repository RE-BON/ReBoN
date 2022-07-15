package com.handong.rebon.shop.infrastructure.dto;

public class ResultDto {
    private PlaceDto place;

    public ResultDto() {
    }

    public ResultDto(PlaceDto place) {
        this.place = place;
    }

    public PlaceDto getPlace() {
        return place;
    }
}
