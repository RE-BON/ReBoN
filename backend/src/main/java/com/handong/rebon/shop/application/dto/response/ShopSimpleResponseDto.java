package com.handong.rebon.shop.application.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.shop.domain.Shop;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ShopSimpleResponseDto {
    private Long id;
    private String name;
    private double star;
    private List<ShopTagResponse> tags;

    @Builder
    public ShopSimpleResponseDto(Long id, String name, double star, List<ShopTagResponse> tags) {
        this.id = id;
        this.name = name;
        this.star = star;
        this.tags = tags;
    }

    public static ShopSimpleResponseDto from(Shop shop) {
        List<ShopTagResponse> tags = shop.getShopTags().stream()
                                         .map(shopTag -> ShopTagResponse.from(shopTag.getTag()))
                                         .collect(Collectors.toList());

        return ShopSimpleResponseDto.builder()
                                    .id(shop.getId())
                                    .name(shop.getName())
                                    .star(shop.getStar())
                                    .tags(tags)
                                    .build();
    }
}
