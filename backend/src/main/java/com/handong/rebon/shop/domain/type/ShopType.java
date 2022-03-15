package com.handong.rebon.shop.domain.type;

import java.util.Arrays;
import java.util.List;

import com.handong.rebon.exception.shop.NoSuchCategoryException;
import com.handong.rebon.menu.domain.Menu;
import com.handong.rebon.shop.domain.CreateShop;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.location.Location;

import lombok.Getter;

@Getter
public enum ShopType {
    RESTAURANT("식당", (category, name, businessHour, phone, address, longitude, latitude, shopImages, menus)
            -> Restaurant.builder()
                         .category(category)
                         .shopContent(new ShopContent(name, businessHour, phone))
                         .location(new Location(address, longitude, latitude))
                         .shopImages(shopImages)
                         .menus(menus)
                         .build()),

    CAFE("카페", (category, name, businessHour, phone, address, longitude, latitude, shopImages, menus)
            -> Cafe.builder()
                   .category(category)
                   .shopContent(new ShopContent(name, businessHour, phone))
                   .location(new Location(address, longitude, latitude))
                   .shopImages(shopImages)
                   .menus(menus)
                   .build()),

    LODGING("숙소", (category, name, businessHour, phone, address, longitude, latitude, shopImages, menus)
            -> Lodging.builder()
                      .category(category)
                      .shopContent(new ShopContent(name, businessHour, phone))
                      .location(new Location(address, longitude, latitude))
                      .shopImages(shopImages)
                      .build());

    private String name;
    private CreateShop createShop;

    ShopType(String name, CreateShop createShop) {
        this.name = name;
        this.createShop = createShop;
    }

    public static ShopType findByCategory(Category category) {
        return Arrays.stream(ShopType.values())
                     .filter(shopType -> category.isSameName(shopType.name))
                     .findFirst()
                     .orElseThrow(NoSuchCategoryException::new);
    }

    public Shop create(
            Category category,
            String name,
            String businessHour,
            String phone,
            String address,
            String longitude,
            String latitude,
            ShopImages shopImages,
            List<Menu> menus
    ) {
        return this.createShop.create(category, name, businessHour, phone, address, longitude, latitude, shopImages, menus);
    }
}
