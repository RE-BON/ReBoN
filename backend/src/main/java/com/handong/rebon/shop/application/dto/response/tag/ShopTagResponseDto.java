package com.handong.rebon.shop.application.dto.response.tag;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.tag.domain.Tag;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShopTagResponseDto {
    private Long id;
    private String name;

    public ShopTagResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ShopTagResponseDto from(Tag tag) {
        return new ShopTagResponseDto(tag.getId(), tag.getName());
    }

    public static List<ShopTagResponseDto> toDtos(Shop shop) {
        return shop.getShopTags().stream()
                   .map(shopTag -> ShopTagResponseDto.from(shopTag.getTag()))
                   .collect(Collectors.toList());
    }
}
