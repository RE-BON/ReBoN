package com.handong.rebon.shop.presentation.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.shop.application.dto.response.ShopTagResponse;
import com.handong.rebon.shop.domain.Shop;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShopSimpleResponse {
    private Long id;
    private String name;
    private double star;
    private List<ShopTagResponse> tags;
    private String image;

    @Builder
    public ShopSimpleResponse(Long id, String name, double star, List<ShopTagResponse> tags, String image) {
        this.id = id;
        this.name = name;
        this.star = star;
        this.tags = tags;
        this.image = image;
    }

    public static ShopSimpleResponse from(Shop shop) {
        List<ShopTagResponse> tags = shop.getShopTags().stream()
                                         .map(shopTag -> ShopTagResponse.from(shopTag.getTag()))
                                         .collect(Collectors.toList());

        return ShopSimpleResponse.builder()
                                 .id(shop.getId())
                                 .name(shop.getName())
                                 .star(shop.getStar())
                                 .tags(tags)
                                 .image(shop.getMainImage())
                                 .build();
    }
}
