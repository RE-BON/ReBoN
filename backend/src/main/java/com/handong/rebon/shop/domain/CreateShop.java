package com.handong.rebon.shop.domain;

import java.util.List;

import com.handong.rebon.menu.domain.Menu;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.content.ShopImages;

@FunctionalInterface
public interface CreateShop {
    Shop create(
            Category category,
            String name,
            String businessHour,
            String phone,
            String address,
            String longitude,
            String latitude,
            ShopImages shopImages,
            List<Menu> menus
    );
}
