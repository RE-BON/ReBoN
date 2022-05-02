package com.handong.rebon.shop.application;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.category.application.CategoryService;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.common.ImageUploader;
import com.handong.rebon.exception.shop.NoSuchShopException;
import com.handong.rebon.shop.application.adapter.ShopServiceAdapter;
import com.handong.rebon.shop.application.dto.request.ShopCreateRequestDto;
import com.handong.rebon.shop.application.dto.request.ShopSearchDto;
import com.handong.rebon.shop.application.dto.response.ShopResponseDto;
import com.handong.rebon.shop.application.dto.response.ShopSimpleResponseDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.ShopSearchCondition;
import com.handong.rebon.shop.domain.content.ShopImage;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.repository.ShopRepository;
import com.handong.rebon.tag.application.TagService;
import com.handong.rebon.tag.domain.Tag;

import org.springframework.data.domain.Page;
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
    private final ImageUploader imageUploader;

    @Transactional
    public Long create(ShopCreateRequestDto shopCreateRequestDto) {
        Category category = categoryService.findById(shopCreateRequestDto.getCategoryId());

        List<Category> subCategories = categoryService.findAllContainIds(shopCreateRequestDto.getSubCategories());
        List<Tag> tags = tagService.findAllContainIds(shopCreateRequestDto.getTags());

        ShopImages shopImages = saveImages(shopCreateRequestDto.getImages());

        ShopServiceAdapter adapter = shopAdapterService.shopAdapterByCategory(category);
        Shop shop = adapter.create(shopImages, shopCreateRequestDto);

        shop.addTags(tags);
        shop.addCategories(category, subCategories);

        Shop savedShop = shopRepository.save(shop);
        return savedShop.getId();
    }

    private ShopImages saveImages(List<MultipartFile> images) {
        List<String> urls = imageUploader.saveAll(images);
        List<ShopImage> shopImages = urls.stream()
                .map(ShopImage::new)
                .collect(Collectors.toList());
        return ShopImages.of(shopImages);
    }

    @Transactional(readOnly = true)
    public List<ShopSimpleResponseDto> search(ShopSearchDto shopSearchDto) {
        Tag tag = tagService.findById(shopSearchDto.getTag());

        Category category = categoryService.findById(shopSearchDto.getCategory());
        List<Category> subs = categoryService.findAllContainIds(shopSearchDto.getSubCategories());

        ShopSearchCondition shopSearchCondition = new ShopSearchCondition(tag, category, subs);

        Page<Shop> results =
                shopRepository.searchShopByConditionApplyPage(shopSearchCondition, shopSearchDto.getPageable());

        return results.stream()
                .map(ShopSimpleResponseDto::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ShopResponseDto findById(Long id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(NoSuchShopException::new);

        ShopServiceAdapter adapter = shopAdapterService.shopAdapterByCategory(shop.getCategory());
        return adapter.convertToShopResponseDto(shop);
    }
}