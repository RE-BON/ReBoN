package com.handong.rebon.shop.application.adapter;

import java.util.ArrayList;
import java.util.List;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.shop.application.MenuService;
import com.handong.rebon.shop.application.dto.request.ShopRequestDto;
import com.handong.rebon.shop.application.dto.response.ShopResponseDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.menu.Menu;
import com.handong.rebon.shop.domain.type.Cafe;
import com.handong.rebon.shop.infrastructure.dto.ShopInfoDto;

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
    public Shop create(ShopImages shopImages, ShopRequestDto data) {
        ShopContent content = new ShopContent(data.getName(), data.getStart(), data.getEnd(), data.getPhone());
        ShopScore score = new ShopScore(0.0, 0, 0);
        Cafe cafe = Cafe.builder()
                        .shopContent(content)
                        .address(data.getAddress())
                        .shopImages(shopImages)
                        .shopScore(score)
                        .build();

        List<Menu> menus = menuService.createMenus(data.getMenus());
        cafe.addMenu(menus);
        return cafe;
    }

    @Override
    public ShopResponseDto convertToShopResponseDto(Shop shop) {
        Cafe cafe = (Cafe) shop;
        List<Menu> menus = cafe.getMenus();
        return ShopResponseDto.of(shop, menus);
    }

    @Override
    public void update(Shop shop, ShopRequestDto shopRequestDto) {
        Cafe cafe = (Cafe) shop;
        List<Menu> menu = menuService.createMenus(shopRequestDto.getMenus());
        cafe.updateMenu(menu);
    }

    @Override
    public Shop createNaverShop(ShopImages shopImages, ShopInfoDto data) {
        ShopContent content = new ShopContent(data.getName(), data.getBizhours(), data.getTel());
        ShopScore score = new ShopScore(0.0, 0, 0);
        Cafe cafe = Cafe.builder()
                        .shopContent(content)
                        .address(data.getRoadAddress())
                        .shopImages(shopImages)
                        .shopScore(score)
                        .naverId(data.getId())
                        .build();

        List<Menu> menus = new ArrayList<>();
        if (data.getMenuExist() == 1) {
            menus = menuService.createMenus(data.getMenuInfo());
        }
        cafe.addMenu(menus);
        return cafe;
    }
}
