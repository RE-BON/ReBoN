package com.handong.rebon.shop.application.dto;

import java.util.List;

import com.handong.rebon.shop.presentation.dto.request.MenuGroupRequest;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopRequestDto {
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
}
