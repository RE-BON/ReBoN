package com.handong.rebon.shop.application;

import java.util.ArrayList;
import java.util.List;

import com.handong.rebon.menu.domain.Menu;
import com.handong.rebon.shop.application.dto.ShopRequestDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.repository.ShopRepository;
import com.handong.rebon.shop.domain.tag.Tag;
import com.handong.rebon.shop.domain.type.ShopType;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShopService {
    private final ShopRepository shopRepository;

    @Transactional
    public Long register(ShopRequestDto shopRequestDto) {
        // TODO 카테고리 가져오기
        // Category category = categoryService.findById(shopRequest.getCategoryId());
        Category category = new Category("식당");

        // TODO 태그 가져오기
        // tagService.findTags(shopRequest.getTags());
        List<Tag> tags = new ArrayList<>();

        // TODO 이미지 저장
        ShopImages shopImages = saveImages(shopRequestDto.getImages());

        // TODO 메뉴 등록
        // menuService.registerMenus(shopRequest.getMenus());
        List<Menu> menus = new ArrayList<>();

        ShopType type = ShopType.findByCategory(category);
        Shop shop = create(shopRequestDto, category, shopImages, menus, type);
        shop.addTags(tags);

        Shop savedShop = shopRepository.save(shop);
        return savedShop.getId();
    }

    private ShopImages saveImages(List<MultipartFile> images) {
        return new ShopImages();
    }

    private Shop create(ShopRequestDto shopRequest, Category category, ShopImages shopImages, List<Menu> menus, ShopType type) {
        return type.create(
                category,
                shopRequest.getName(),
                shopRequest.getBusinessHour(),
                shopRequest.getPhone(),
                shopRequest.getAddress(),
                shopRequest.getLongitude(),
                shopRequest.getLatitude(),
                shopImages,
                menus
        );
    }
}
