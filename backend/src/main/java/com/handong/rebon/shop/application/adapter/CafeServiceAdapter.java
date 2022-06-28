package com.handong.rebon.shop.application.adapter;

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
import com.handong.rebon.shop.domain.type.Cafe;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CafeServiceAdapter implements ShopServiceAdapter {
    private final MenuGroupService menuGroupService;

    @Override
    public boolean supports(Category category) {
        return category.isSameName("카페");
    }

    @Override
    public Shop create(ShopImages shopImages, ShopRequestDto data) {
        Cafe cafe = Cafe.builder()
                        .shopContent(new ShopContent(data.getName(), data.getStart(), data.getEnd(), data.getPhone()))
                        .location(new Location(data.getAddress(), data.getLongitude(), data.getLatitude()))
                        .shopImages(shopImages)
                        .shopScore(new ShopScore(0.0, 0))
                        .build();

        List<Menu> menus = menuGroupService.createMenu(cafe, data.getMenus());
        cafe.addMenu(menus);
        return cafe;
    }

    @Override
    public ShopResponseDto convertToShopResponseDto(Shop shop) {
        Cafe cafe = (Cafe) shop;
        Map<MenuGroup, List<Menu>> menuGroups = cafe.getMenuGroupByMenuGroup();
        return ShopResponseDto.of(shop, menuGroups);
    }

    @Override
    public void update(Shop shop, ShopRequestDto shopRequestDto) {
        Cafe cafe = (Cafe) shop;
        List<Menu> menu = menuGroupService.createMenu(cafe, shopRequestDto.getMenus());
        cafe.updateMenu(menu);
    }
}
