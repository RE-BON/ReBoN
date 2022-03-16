package com.handong.rebon.shop.domain.adapter;

import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.ShopData;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.type.Restaurant;

import org.springframework.stereotype.Component;

@Component
public class RestaurantAdapter implements ShopAdapter {

    @Override
    public boolean supports(Category category) {
        return category.isSameName("식당");
    }

    @Override
    public Shop create(ShopData data) {
        return Restaurant.builder()
                         .category(data.getCategory())
                         .shopContent(data.getShopContent())
                         .location(data.getLocation())
                         .shopImages(data.getShopImages())
                         .menus(data.getMenus())
                         .build();
    }
}
