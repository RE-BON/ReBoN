package com.handong.rebon.shop.application.dto;

import java.util.List;

import com.handong.rebon.menu.domain.Menu;
import com.handong.rebon.menu.presentation.dto.request.MenuGroupRequest;
import com.handong.rebon.shop.domain.ShopData;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.location.Location;

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
    private String name;
    private String businessHour;
    private String phone;
    private String address;
    private String longitude;
    private String latitude;
    private List<MultipartFile> images;
    private List<String> tags;
    private List<MenuGroupRequest> menus;

    public ShopData toShopData(Category category, ShopImages shopImages, List<Menu> menus) {
        return ShopData.builder()
                       .category(category)
                       .shopContent(new ShopContent(name, businessHour, phone))
                       .location(new Location(address, longitude, latitude))
                       .shopImages(shopImages)
                       .menus(menus)
                       .build();
    }
}
