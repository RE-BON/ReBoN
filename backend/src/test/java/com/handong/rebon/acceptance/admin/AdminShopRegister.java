package com.handong.rebon.acceptance.admin;

import java.util.Collections;
import java.util.List;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.shop.application.ShopAdapterService;
import com.handong.rebon.shop.application.adapter.ShopServiceAdapter;
import com.handong.rebon.shop.application.dto.request.ShopCreateRequestDto;
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
}
