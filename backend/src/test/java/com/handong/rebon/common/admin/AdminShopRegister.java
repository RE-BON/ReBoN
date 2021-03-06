package com.handong.rebon.common.admin;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.common.factory.ImageFactory;
import com.handong.rebon.shop.application.ShopAdapterService;
import com.handong.rebon.shop.application.ShopService;
import com.handong.rebon.shop.application.adapter.ShopServiceAdapter;
import com.handong.rebon.shop.application.dto.request.ShopRequestDto;
import com.handong.rebon.shop.application.dto.request.menu.MenuGroupRequestDto;
import com.handong.rebon.shop.application.dto.request.menu.MenuRequestDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.repository.ShopRepository;
import com.handong.rebon.tag.domain.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

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
        ShopRequestDto shopRequestDto = ShopRequestDto.builder()
                                                      .name(name)
                                                      .menus(Collections.emptyList())
                                                      .build();

        ShopServiceAdapter adapter = shopAdapterService.shopAdapterByCategory(parent);
        Shop shop = adapter.create(shopImages, shopRequestDto);

        shop.addCategories(parent, subs);
        shop.addTags(tags);

        return shopRepository.save(shop);
    }

    public Shop CafeRegisterWithMenu(
            String name,
            Category parent,
            List<Category> subs,
            List<Tag> tags,
            LocalTime start,
            LocalTime end
    ) {
        List<Long> subIds = subs.stream()
                                .map(Category::getId)
                                .collect(Collectors.toList());

        List<Long> tagIds = tags.stream()
                                .map(Tag::getId)
                                .collect(Collectors.toList());

        ShopRequestDto shopRequestDto = ShopRequestDto.builder()
                                                      .categoryId(parent.getId())
                                                      .subCategories(subIds)
                                                      .tags(tagIds)
                                                      .name(name)
                                                      .start(start)
                                                      .end(end)
                                                      .phone("010-1234-5678")
                                                      .address("???????????? ????????? OO")
                                                      .images(basicImage())
                                                      .menus(basicCafeMenu())
                                                      .build();

        Long id = shopService.create(shopRequestDto);
        return shopRepository.getById(id);
    }

    private List<MultipartFile> basicImage() {
        return List.of(
                ImageFactory.createFakeImage("?????? ??????"),
                ImageFactory.createFakeImage("?????? ??????1"),
                ImageFactory.createFakeImage("?????? ??????2")
        );
    }

    private List<MenuGroupRequestDto> basicCafeMenu() {
        List<MenuRequestDto> coffee = Arrays.asList(
                new MenuRequestDto("???????????????", 5000),
                new MenuRequestDto("?????? ??????", 6000),
                new MenuRequestDto("???????????????", 4000)
        );

        List<MenuRequestDto> dessert = Arrays.asList(
                new MenuRequestDto("?????? ?????????", 7000),
                new MenuRequestDto("?????? ?????????", 7000)
        );

        return List.of(
                new MenuGroupRequestDto("?????????", coffee),
                new MenuGroupRequestDto("????????????", dessert)
        );
    }

    public void delete(Shop shop) {
        shopService.delete(shop.getId());
    }
}
