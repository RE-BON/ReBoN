package com.handong.rebon.shop.application.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.shop.domain.Shop;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShopSimpleResponseDto {
    private Long id;
    private String name;
    private double star;
    private List<ShopTagResponseDto> tags;
    private String image;

    @Builder
    public ShopSimpleResponseDto(Long id, String name, double star, List<ShopTagResponseDto> tags, String image) {
        this.id = id;
        this.name = name;
        this.star = star;
        this.tags = tags;
        this.image = image;
    }

    public static ShopSimpleResponseDto from(Shop shop) {
        List<ShopTagResponseDto> tags = shop.getShopTags().stream()
                                            .map(shopTag -> ShopTagResponseDto.from(shopTag.getTag()))
                                            .collect(Collectors.toList());

        return ShopSimpleResponseDto.builder()
                                    .id(shop.getId())
                                    .name(shop.getName())
                                    .star(shop.getStar())
                                    .tags(tags)
                                    .image(shop.getMainImage())
                                    .build();
    }
}
