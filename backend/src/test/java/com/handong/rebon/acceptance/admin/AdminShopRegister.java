package com.handong.rebon.acceptance.admin;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.shop.application.ShopAdapterService;
import com.handong.rebon.shop.application.ShopService;
import com.handong.rebon.shop.application.adapter.ShopServiceAdapter;
import com.handong.rebon.shop.application.dto.request.ShopCreateRequestDto;
import com.handong.rebon.shop.application.dto.request.menu.MenuGroupRequestDto;
import com.handong.rebon.shop.application.dto.request.menu.MenuRequestDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.repository.ShopRepository;
import com.handong.rebon.tag.domain.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@Component
@ActiveProfiles("test")
public class AdminShopRegister {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopAdapterService shopAdapterService;

    public Shop simpleRegister(String name, Category parent, List<Category> subs, List<Tag> tags, ShopImages shopImages) {
        ShopCreateRequestDto shopCreateRequestDto = ShopCreateRequestDto.builder()
                                                                        .name(name)
                                                                        .menus(Collections.emptyList())
                                                                        .build();

        ShopServiceAdapter adapter = shopAdapterService.shopAdapterByCategory(parent);
        Shop shop = adapter.create(shopImages, shopCreateRequestDto);

        shop.addCategories(parent, subs);
        shop.addTags(tags);

        return shopRepository.save(shop);
    }

    public Shop CafeRegisterWithMenu(String name, Category parent, List<Category> subs, List<Tag> tags, ShopImages shopImages) {
        List<Long> subIds = subs.stream()
                                .map(Category::getId)
                                .collect(Collectors.toList());

        List<Long> tagIds = tags.stream()
                                .map(Tag::getId)
                                .collect(Collectors.toList());

        ShopCreateRequestDto shopCreateRequestDto = ShopCreateRequestDto.builder()
                                                                        .categoryId(parent.getId())
                                                                        .subCategories(subIds)
                                                                        .tags(tagIds)
                                                                        .name(name)
                                                                        .businessHour("10:00 - 20:00")
                                                                        .phone("010-1234-5678")
                                                                        .address("경상북도 포항시 OO")
                                                                        .latitude("41.40338")
                                                                        .longitude("2.17403")
                                                                        //  .images() TODO 이미지 기능
                                                                        .menus(basicCafeMenu())
                                                                        .build();

        Long id = shopService.create(shopCreateRequestDto);
        return shopRepository.findById(id).get();
    }

    private List<MenuGroupRequestDto> basicCafeMenu() {
        List<MenuRequestDto> coffee = Arrays.asList(
                new MenuRequestDto("아메리카노", 5000),
                new MenuRequestDto("딸기 라떼", 6000),
                new MenuRequestDto("에스프레소", 4000)
        );

        List<MenuRequestDto> dessert = Arrays.asList(
                new MenuRequestDto("딸기 케이크", 7000),
                new MenuRequestDto("초코 케이크", 7000)
        );

        return Arrays.asList(
                new MenuGroupRequestDto("커피류", coffee),
                new MenuGroupRequestDto("디저트류", dessert)
        );
    }
}
