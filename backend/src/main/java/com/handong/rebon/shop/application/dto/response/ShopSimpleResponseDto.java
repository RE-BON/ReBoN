package com.handong.rebon.shop.application.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.auth.domain.LoginMember;
import com.handong.rebon.shop.application.dto.response.tag.ShopTagResponseDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.like.Likes;

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
    private boolean like;
    private List<ShopTagResponseDto> tags;
    private String image;

    @Builder
    public ShopSimpleResponseDto(Long id, String name, double star, boolean like, List<ShopTagResponseDto> tags, String image) {
        this.id = id;
        this.name = name;
        this.star = star;
        this.like = like;
        this.tags = tags;
        this.image = image;
    }

    public static ShopSimpleResponseDto of(Shop shop, LoginMember loginMember) {

        return ShopSimpleResponseDto.builder()
                                    .id(shop.getId())
                                    .name(shop.getName())
                                    .star(shop.getStar())
                                    .like(shop.containLike(loginMember))
                                    .tags(ShopTagResponseDto.toDtos(shop))
                                    .image(shop.getMainImage())
                                    .build();
    }

    public static ShopSimpleResponseDto from(Likes like) {

        return ShopSimpleResponseDto.builder()
                                    .id(like.getShop().getId())
                                    .name(like.getShop().getName())
                                    .star(like.getShop().getStar())
                                    .like(true)
                                    .tags(ShopTagResponseDto.toDtos(like.getShop()))
                                    .image(like.getShop().getMainImage())
                                    .build();
    }
}
