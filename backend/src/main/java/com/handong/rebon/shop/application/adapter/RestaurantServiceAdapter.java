package com.handong.rebon.shop.application.adapter;

import java.util.List;

import com.handong.rebon.menu.application.MenuService;
import com.handong.rebon.menu.domain.Menu;
import com.handong.rebon.shop.application.dto.ShopRequestDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.location.Location;
import com.handong.rebon.shop.domain.type.Restaurant;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class RestaurantServiceAdapter implements ShopServiceAdapter {
    private final MenuService menuService;

    @Override
    public boolean supports(Category category) {
        return category.isSameName("식당");
    }

    @Override
    public Shop create(ShopImages shopImages, ShopRequestDto data) {
        Restaurant restaurant = Restaurant.builder()
                                          .shopContent(new ShopContent(data.getName(), data.getBusinessHour(), data.getPhone()))
                                          .location(new Location(data.getAddress(), data.getLongitude(), data.getLatitude()))
                                          .shopImages(shopImages)
                                          .shopScore(new ShopScore(0.0, 0))
                                          .build();

        List<Menu> menus = menuService.createMenu(data.getMenus());
        restaurant.addMenu(menus);
        return restaurant;
    }

}
