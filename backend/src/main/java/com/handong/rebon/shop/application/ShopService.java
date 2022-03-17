package com.handong.rebon.shop.application;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.handong.rebon.shop.application.dto.ShopRequestDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.application.adapter.ShopServiceAdapter;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.category.CategoryService;
import com.handong.rebon.shop.domain.content.ShopImage;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.repository.ShopRepository;
import com.handong.rebon.shop.domain.tag.Tag;
import com.handong.rebon.shop.domain.tag.TagService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShopService {
    private final CategoryService categoryService;
    private final TagService tagService;
    private final ShopAdapterService shopAdapterService;
    private final ShopRepository shopRepository;

    @Transactional
    public Long create(ShopRequestDto shopRequestDto) {
        // TODO 카테고리 가져오기
        Category category = categoryService.findById(shopRequestDto.getCategoryId());
        List<Category> subCategories = categoryService.findAll(shopRequestDto.getSubCategories());

        // TODO 태그 가져오기
        List<Tag> tags = tagService.findAll(shopRequestDto.getTags());

        // TODO 이미지 저장
        ShopImages shopImages = saveImages(shopRequestDto.getImages());

        ShopServiceAdapter adapter = shopAdapterService.shopAdapterByCategory(category);
        Shop shop = adapter.create(shopImages, shopRequestDto);

        shop.addTags(tags);
        shop.addCategories(category, subCategories);

        Shop savedShop = shopRepository.save(shop);
        return savedShop.getId();
    }

    // TODO 이미지 저장 기능 구현(따로 서비스로 분리해도 될듯?)
    private ShopImages saveImages(List<MultipartFile> images) {
        return new ShopImages(
                Arrays.asList(
                        new ShopImage(1L, "url"),
                        new ShopImage(2L, "url")
                )
        );
    }
}
