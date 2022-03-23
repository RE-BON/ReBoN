package com.handong.rebon.shop.domain.tag.application.dto;

import lombok.Getter;

@Getter
public class TagRequestDto {
    private String name;

    public TagRequestDto(String name){
        this.name = name;
    }
}
