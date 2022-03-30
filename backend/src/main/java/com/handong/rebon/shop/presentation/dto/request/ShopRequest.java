package com.handong.rebon.shop.presentation.dto.request;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.shop.application.dto.request.ShopCreateRequestDto;
import com.handong.rebon.shop.application.dto.request.menu.MenuGroupRequestDto;
import com.handong.rebon.shop.application.dto.request.menu.MenuRequestDto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShopRequest {
    private Long categoryId;
    private List<Long> subCategories;
    private String name;
    private String businessHour;
    private String phone;
    private String address;
    private String longitude;
    private String latitude;
    private List<MultipartFile> images;
    private List<Long> tags;
    private List<MenuGroupRequest> menus;

    public ShopCreateRequestDto toDto() {
        List<MenuGroupRequestDto> menuGroupRequestDtos = menus.stream()
                                                              .map(this::toDto)
                                                              .collect(Collectors.toList());

        return ShopCreateRequestDto.builder()
                                   .categoryId(categoryId)
                                   .subCategories(subCategories)
                                   .name(name)
                                   .businessHour(businessHour)
                                   .phone(phone)
                                   .address(address)
                                   .longitude(longitude)
                                   .latitude(latitude)
                                   .images(images)
                                   .tags(tags)
                                   .menus(menuGroupRequestDtos)
                                   .build();
    }

    private MenuGroupRequestDto toDto(MenuGroupRequest menuGroupRequest) {
        List<MenuRequestDto> menuRequestDtos = menuGroupRequest
                .getMenus()
                .stream()
                .map(menuRequest -> new MenuRequestDto(menuRequest.getName(), menuRequest.getPrice()))
                .collect(Collectors.toList());
        return new MenuGroupRequestDto(menuGroupRequest.getName(), menuRequestDtos);
    }
}
