package com.handong.rebon.shop.application.dto.response;

import com.handong.rebon.tag.domain.Tag;

import lombok.Getter;

@Getter
public class ShopTagResponse {
    private Long id;
    private String name;

    public ShopTagResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ShopTagResponse from(Tag tag) {
        return new ShopTagResponse(tag.getId(), tag.getName());
    }
}
