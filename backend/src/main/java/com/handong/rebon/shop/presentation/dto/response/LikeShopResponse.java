package com.handong.rebon.shop.presentation.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.shop.application.dto.response.LikeShopResponseDto;
import com.handong.rebon.shop.presentation.dto.response.tag.ShopTagResponse;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeShopResponse {
    private Long id;
    private String name;
    private Long categoryId;
    private String categoryName;
    private double star;
    private boolean like;
    private List<ShopTagResponse> tags;
    private String image;

    @Builder
    public LikeShopResponse(Long id, String name, Long categoryId, String categoryName,
                            double star, boolean like, List<ShopTagResponse> tags, String image) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.star = star;
        this.like = like;
        this.tags = tags;
        this.image = image;
    }

    public static List<LikeShopResponse> convert(List<LikeShopResponseDto> responses) {
        return responses.stream()
                        .map(LikeShopResponse::from)
                        .collect(Collectors.toList());
    }

    public static LikeShopResponse from(LikeShopResponseDto dto) {
        List<ShopTagResponse> tags = dto.getTags().stream()
                                        .map(ShopTagResponse::from)
                                        .collect(Collectors.toList());
        return LikeShopResponse.builder()
                               .id(dto.getId())
                               .name(dto.getName())
                               .star(dto.getStar())
                               .categoryId(dto.getCategoryId())
                               .categoryName(dto.getCategoryName())
                               .like(dto.isLike())
                               .tags(tags)
                               .image(dto.getImage())
                               .build();
    }
}
