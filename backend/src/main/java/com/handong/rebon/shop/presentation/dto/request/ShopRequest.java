package com.handong.rebon.shop.presentation.dto.request;

import java.util.List;

import com.handong.rebon.menu.presentation.dto.request.MenuGroupRequest;
import com.handong.rebon.shop.application.dto.ShopRequestDto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShopRequest {
    private Long categoryId;
    private String name;
    private String businessHour;
    private String phone;
    private String address;
    private String longitude;
    private String latitude;
    private List<MultipartFile> images;
    private List<Long> tags;
    private List<MenuGroupRequest> menus;

    public ShopRequestDto toDto() {
        return ShopRequestDto.builder()
                             .categoryId(categoryId)
                             .name(name)
                             .businessHour(businessHour)
                             .phone(phone)
                             .address(address)
                             .longitude(longitude)
                             .latitude(latitude)
                             .images(images)
                             .tags(tags)
                             .menus(menus)
                             .build();
    }
}
