package com.handong.rebon.shop.application;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.category.Category;
import com.handong.rebon.category.CategoryService;
import com.handong.rebon.shop.application.adapter.ShopServiceAdapter;
import com.handong.rebon.shop.application.dto.request.ShopCreateRequestDto;
import com.handong.rebon.shop.application.dto.request.ShopRequestDto;
import com.handong.rebon.shop.application.dto.response.ShopSimpleResponseDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.content.ShopImage;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.repository.ShopImageRepository;
import com.handong.rebon.shop.domain.repository.ShopRepository;
import com.handong.rebon.tag.application.TagService;
import com.handong.rebon.tag.domain.Tag;

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

    private final ShopImageRepository shopImageRepository;

    @Transactional
    public Long create(ShopCreateRequestDto shopCreateRequestDto) {
        // TODO 카테고리 가져오기
        Category category = categoryService.findById(shopCreateRequestDto.getCategoryId());
        List<Category> subCategories = categoryService.findAll(shopCreateRequestDto.getSubCategories());

        // TODO 태그 가져오기
        List<Tag> tags = tagService.findAll(shopCreateRequestDto.getTags());

        // TODO 이미지 저장
        ShopImages shopImages = saveImages(shopCreateRequestDto.getImages());

        ShopServiceAdapter adapter = shopAdapterService.shopAdapterByCategory(category);
        Shop shop = adapter.create(shopImages, shopCreateRequestDto);

        shop.addTags(tags);
        shop.addCategories(category, subCategories);

        Shop savedShop = shopRepository.save(shop);
        return savedShop.getId();
    }

    // TODO 이미지 저장 기능 구현(따로 서비스로 분리해도 될듯?)
    private ShopImages saveImages(List<MultipartFile> images) {
        ShopImage url1 = new ShopImage("url1");
        ShopImage url2 = new ShopImage("url2");

        shopImageRepository.save(url1);
        shopImageRepository.save(url2);

        return new ShopImages(Arrays.asList(url1, url2));
    }

    @Transactional(readOnly = true)
    public List<ShopSimpleResponseDto> findByCategory(ShopRequestDto shopRequestDto) {
        // TODO 카테고리 가져오기
        Category category = categoryService.findById(shopRequestDto.getCategoryId());

        List<Shop> shops = shopRepository.findAllByCategory(category);

        return shops.stream()
                    .map(ShopSimpleResponseDto::from)
                    .collect(Collectors.toList());
    }
}
