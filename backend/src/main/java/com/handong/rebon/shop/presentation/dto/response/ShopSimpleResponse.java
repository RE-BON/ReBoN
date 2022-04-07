package com.handong.rebon.shop.presentation.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.shop.application.dto.response.ShopSimpleResponseDto;
import com.handong.rebon.shop.presentation.dto.response.tag.ShopTagResponse;

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

    public static List<ShopSimpleResponse> convert(List<ShopSimpleResponseDto> responses) {
        return responses.stream()
                        .map(ShopSimpleResponse::from)
                        .collect(Collectors.toList());
    }

    private static ShopSimpleResponse from(ShopSimpleResponseDto dto) {
        List<ShopTagResponse> tags = dto.getTags().stream()
                                        .map(ShopTagResponse::from)
                                        .collect(Collectors.toList());

        return ShopSimpleResponse.builder()
                                 .id(dto.getId())
                                 .name(dto.getName())
                                 .star(dto.getStar())
                                 .tags(tags)
                                 .image(dto.getImage())
                                 .build();
    }
}
