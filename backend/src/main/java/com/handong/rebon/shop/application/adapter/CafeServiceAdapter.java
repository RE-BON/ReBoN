package com.handong.rebon.shop.application.adapter;

import java.util.List;

import com.handong.rebon.menu.application.MenuService;
import com.handong.rebon.menu.domain.Menu;
import com.handong.rebon.shop.application.dto.ShopRequestDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.location.Location;
import com.handong.rebon.shop.domain.type.Cafe;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CafeServiceAdapter implements ShopServiceAdapter {
    private final MenuService menuService;

    @Override
    public boolean supports(Category category) {
        return category.isSameName("카페");
    }

    @Override
    public Shop create(Category category, ShopImages shopImages, ShopRequestDto data) {
        Cafe cafe = Cafe.builder()
                        .category(category)
                        .shopContent(new ShopContent(data.getName(), data.getBusinessHour(), data.getPhone()))
                        .location(new Location(data.getAddress(), data.getLongitude(), data.getLatitude()))
                        .shopImages(shopImages)
                        .build();

        List<Menu> menus = menuService.createMenu(data.getMenus());
        cafe.addMenu(menus);
        return cafe;
    }
}
