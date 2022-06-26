package com.handong.rebon.shop.application.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.shop.application.dto.response.tag.ShopTagResponseDto;
import com.handong.rebon.shop.domain.Shop;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeShopResponseDto {
    private Long id;
    private String name;
    private Long categoryId;
    private String categoryName;
    private double star;
    private boolean like;
    private List<ShopTagResponseDto> tags;
    private String image;

    @Builder
    public LikeShopResponseDto(Long id, String name, Category category, double star, boolean like, List<ShopTagResponseDto> tags, String image) {
        this.id = id;
        this.name = name;
        this.categoryId = category.getId();
        this.categoryName = category.getName();
        this.star = star;
        this.like = true;
        this.tags = tags;
        this.image = image;
    }

    public static LikeShopResponseDto from(Shop shop) {
        List<ShopTagResponseDto> tags = shop.getShopTags().stream()
                                            .map(shopTag -> ShopTagResponseDto.from(shopTag.getTag()))
                                            .collect(Collectors.toList());

        return LikeShopResponseDto.builder()
                                  .id(shop.getId())
                                  .name(shop.getName())
                                  .category(shop.getCategory())
                                  .star(shop.getStar())
                                  .tags(tags)
                                  .image(shop.getMainImage())
                                  .build();
    }
}
