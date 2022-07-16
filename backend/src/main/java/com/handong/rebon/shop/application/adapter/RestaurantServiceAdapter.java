package com.handong.rebon.shop.application.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.shop.application.MenuGroupService;
import com.handong.rebon.shop.application.dto.request.ShopRequestDto;
import com.handong.rebon.shop.application.dto.response.ShopResponseDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.location.Location;
import com.handong.rebon.shop.domain.menu.Menu;
import com.handong.rebon.shop.domain.menu.MenuGroup;
import com.handong.rebon.shop.domain.type.Restaurant;
import com.handong.rebon.shop.infrastructure.dto.ShopInfoDto;

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
    public Shop create(ShopImages shopImages, ShopRequestDto data) {
        Restaurant restaurant = Restaurant.builder()
                                          .shopContent(new ShopContent(data.getName(), data.getStart(), data.getEnd(), data.getPhone()))
                                          .location(new Location(data.getAddress(), data.getLongitude(), data.getLatitude()))
                                          .shopImages(shopImages)
                                          .shopScore(new ShopScore(0.0, 0))
                                          .build();

        List<Menu> menus = menuGroupService.createMenu(restaurant, data.getMenus());
        restaurant.addMenu(menus);
        return restaurant;
    }

    @Override
    public Shop createNaverShop(ShopImages shopImages, ShopInfoDto data) {
        ShopContent content = new ShopContent(data.getName(), data.getBizhourInfo(), data.getTel());
        Location location = new Location(data.getRoadAddress());
        ShopScore score = new ShopScore(0.0, 0);
        Restaurant restaurant = Restaurant.builder()
                                          .shopContent(content)
                                          .location(location)
                                          .shopImages(shopImages)
                                          .shopScore(score)
                                          .naverId(data.getId())
                                          .build();

        List<Menu> menus = new ArrayList<>();
        if (data.getMenuExist() == 1) {
//            menus = menuGroupService.createMenu(restaurant, data.getMenuInfo());
        }
        restaurant.addMenu(menus);
        return restaurant;
    }

    @Override
    public ShopResponseDto convertToShopResponseDto(Shop shop) {
        Restaurant restaurant = (Restaurant) shop;
        Map<MenuGroup, List<Menu>> menuGroups = restaurant.getMenuGroupByMenuGroup();
        return ShopResponseDto.of(shop, menuGroups);
    }

    @Override
    public void update(Shop shop, ShopRequestDto shopRequestDto) {
        Restaurant restaurant = (Restaurant) shop;
        List<Menu> menu = menuGroupService.createMenu(restaurant, shopRequestDto.getMenus());
        restaurant.updateMenu(menu);
    }
}
