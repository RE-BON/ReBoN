package com.handong.rebon.shop.application.dto.request;

import java.time.LocalTime;
import java.util.List;

import com.handong.rebon.shop.application.dto.request.menu.MenuGroupRequestDto;

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
    private LocalTime start;
    private LocalTime end;
    private String phone;
    private String address;
    private String longitude;
    private String latitude;
    private List<MultipartFile> images;
    private List<Long> tags;
    private List<MenuGroupRequestDto> menus;
}
