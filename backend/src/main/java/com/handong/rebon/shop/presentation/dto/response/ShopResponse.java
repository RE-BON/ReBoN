package com.handong.rebon.shop.presentation.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.shop.application.dto.response.ShopResponseDto;
import com.handong.rebon.shop.application.dto.response.category.ShopCategoryResponseDto;
import com.handong.rebon.shop.application.dto.response.image.ShopImageResponseDto;
import com.handong.rebon.shop.application.dto.response.menu.MenuGroupResponseDto;
import com.handong.rebon.shop.application.dto.response.tag.ShopTagResponseDto;
import com.handong.rebon.shop.presentation.dto.response.category.ShopCategoryResponse;
import com.handong.rebon.shop.presentation.dto.response.image.ShopImageResponse;
import com.handong.rebon.shop.presentation.dto.response.menu.MenuGroupResponse;
import com.handong.rebon.shop.presentation.dto.response.tag.ShopTagResponse;

import lombok.Builder;

@Builder
public class ShopResponse {
    private Long id;
    private String name;
    private double star;
    private List<ShopTagResponse> tags;
    private String phone;
    private List<ShopCategoryResponse> subCategories;
    private String businessHour;
    private List<MenuGroupResponse> menus;
    private String address;
    private String longitude;
    private String latitude;
    private List<ShopImageResponse> images;

    public static ShopResponse from(ShopResponseDto dto) {
        return ShopResponse.builder()
                           .id(dto.getId())
                           .name(dto.getName())
                           .star(dto.getStar())
                           .tags(convertToShopTagResponse(dto.getTags()))
                           .phone(dto.getPhone())
                           .subCategories(convertToShopCategoryResponse(dto.getSubCategories()))
                           .businessHour(dto.getBusinessHour())
                           .menus(convertToMenuResponse(dto.getMenus()))
                           .address(dto.getAddress())
                           .longitude(dto.getLongitude())
                           .latitude(dto.getLatitude())
                           .images(convertToImageResponse(dto.getImages()))
                           .build();
    }

    private static List<ShopTagResponse> convertToShopTagResponse(List<ShopTagResponseDto> tags) {
        return tags.stream()
                   .map(ShopTagResponse::from)
                   .collect(Collectors.toList());
    }

    private static List<ShopCategoryResponse> convertToShopCategoryResponse(List<ShopCategoryResponseDto> subCategories) {
        return subCategories.stream()
                            .map(ShopCategoryResponse::from)
                            .collect(Collectors.toList());
    }

    private static List<MenuGroupResponse> convertToMenuResponse(List<MenuGroupResponseDto> menus) {
        return menus.stream()
                    .map(MenuGroupResponse::from)
                    .collect(Collectors.toList());
    }

    private static List<ShopImageResponse> convertToImageResponse(List<ShopImageResponseDto> images) {
        return images.stream()
                     .map(ShopImageResponse::from)
                     .collect(Collectors.toList());
    }
}
