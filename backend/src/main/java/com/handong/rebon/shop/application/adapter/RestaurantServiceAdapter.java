package com.handong.rebon.shop.application.adapter;

import java.util.List;
import java.util.Map;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.shop.application.MenuGroupService;
import com.handong.rebon.shop.application.dto.request.ShopCreateRequestDto;
import com.handong.rebon.shop.application.dto.response.ShopResponseDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.location.Location;
import com.handong.rebon.shop.domain.menu.Menu;
import com.handong.rebon.shop.domain.menu.MenuGroup;
import com.handong.rebon.shop.domain.type.Restaurant;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class RestaurantServiceAdapter implements ShopServiceAdapter {
    private final MenuGroupService menuGroupService;

    @Override
    public boolean supports(Category category) {
        return category.isSameName("식당");
    }

    @Override
    public Shop create(ShopImages shopImages, ShopCreateRequestDto data) {
        Restaurant restaurant = Restaurant.builder()
                                          .shopContent(new ShopContent(data.getName(), data.getBusinessHour(), data.getPhone()))
                                          .location(new Location(data.getAddress(), data.getLongitude(), data.getLatitude()))
                                          .shopImages(shopImages)
                                          .shopScore(new ShopScore(0.0, 0))
                                          .build();

        List<Menu> menus = menuGroupService.createMenu(restaurant, data.getMenus());
        restaurant.addMenu(menus);
        return restaurant;
    }

    @Override
    public ShopResponseDto convertToShopResponseDto(Shop shop) {
        Restaurant restaurant = (Restaurant) shop;
        Map<MenuGroup, List<Menu>> menuGroups = restaurant.getMenuGroupByMenuGroup();
        return ShopResponseDto.of(shop, menuGroups);
    }

}
