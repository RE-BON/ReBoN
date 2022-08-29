package com.handong.rebon.shop.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.handong.rebon.auth.domain.LoginMember;
import com.handong.rebon.category.application.CategoryService;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.common.ImageUploader;
import com.handong.rebon.exception.shop.NoSuchShopException;
import com.handong.rebon.member.application.MemberService;
import com.handong.rebon.member.domain.Member;
import com.handong.rebon.shop.application.adapter.ShopServiceAdapter;
import com.handong.rebon.shop.application.dto.request.ShopLikeRequestDto;
import com.handong.rebon.shop.application.dto.request.ShopRequestDto;
import com.handong.rebon.shop.application.dto.request.ShopSearchDto;
import com.handong.rebon.shop.application.dto.response.ShopLikeResponseDto;
import com.handong.rebon.shop.application.dto.response.ShopResponseDto;
import com.handong.rebon.shop.application.dto.response.ShopSimpleResponseDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.ShopSearchCondition;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImage;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.repository.ShopImageRepository;
import com.handong.rebon.shop.domain.repository.ShopRepository;
import com.handong.rebon.shop.infrastructure.NaverShopInserter;
import com.handong.rebon.shop.infrastructure.dto.NaverShopDto;
import com.handong.rebon.tag.application.TagService;
import com.handong.rebon.tag.domain.Tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import static com.handong.rebon.util.StringUtil.validatesEmptyList;

@RequiredArgsConstructor
@Service
public class ShopService {
    private static final List<String> TYPES = List.of("식당", "카페", "숙소");

    private final CategoryService categoryService;
    private final TagService tagService;
    private final ShopAdapterService shopAdapterService;
    private final MemberService memberService;
    private final ShopRepository shopRepository;
    private final ImageUploader imageUploader;
    private final ShopImageRepository shopImageRepository;
    private final NaverShopInserter shopInserter;

    @Transactional
    public Long create(ShopRequestDto shopRequestDto) {
        Category category = categoryService.findById(shopRequestDto.getCategoryId());

        List<Category> subCategories = categoryService.findAllContainIds(shopRequestDto.getSubCategories());
        List<Tag> tags = tagService.findAllContainIds(shopRequestDto.getTags());

        ShopImages shopImages = saveImages(shopRequestDto.getImages());

        ShopServiceAdapter adapter = shopAdapterService.shopAdapterByCategory(category);
        Shop shop = adapter.create(shopImages, shopRequestDto);

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

    @Transactional
    public List<ShopSimpleResponseDto> search(ShopSearchDto shopSearchDto) {
        Tag tag = tagService.findById(shopSearchDto.getTag());
        tag.countUp();

        Category category = categoryService.findById(shopSearchDto.getCategory());
        List<Category> subs = categoryService.findAllContainIds(shopSearchDto.getSubCategories());

        ShopSearchCondition shopSearchCondition = new ShopSearchCondition(tag, category, subs, shopSearchDto.isOpen());

        Page<Shop> results =
                shopRepository.searchShopByConditionApplyPage(shopSearchCondition, shopSearchDto.getPageable());

        return results.stream()
                      .map(shop -> ShopSimpleResponseDto.of(shop, shopSearchDto.getLoginMember()))
                      .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ShopResponseDto findOneById(Long id) {
        Shop shop = findById(id);
        ShopServiceAdapter adapter = shopAdapterService.shopAdapterByCategory(shop.getCategory());
        return adapter.convertToShopResponseDto(shop);
    }

    @Transactional
    public void delete(Long id) {
        Shop shop = findById(id);
        shopRepository.delete(shop);
    }

    public Shop findById(Long id) {
        return shopRepository.findById(id)
                             .orElseThrow(NoSuchShopException::new);
    }

    @Transactional
    public Long update(Long id, ShopRequestDto shopRequestDto) {
        Shop shop = findById(id);
        Category category = categoryService.findById(shopRequestDto.getCategoryId());

        if (!shop.sameCategory(category)) {
            delete(id);
            return create(shopRequestDto);
        }

        updateContent(shopRequestDto, shop);
        updateCategory(shopRequestDto, shop, category);
        updateTag(shopRequestDto, shop);
        updateImage(shopRequestDto, shop);

        ShopServiceAdapter adapter = shopAdapterService.shopAdapterByCategory(category);
        adapter.update(shop, shopRequestDto);

        return shop.getId();
    }

    @Transactional
    public ShopLikeResponseDto like(ShopLikeRequestDto shopLikeRequestDto) {
        Member member = memberService.findById(shopLikeRequestDto.getUserId());
        Shop shop = findById(shopLikeRequestDto.getShopId());
        shop.like(member);
        return new ShopLikeResponseDto(shop.getLikeCount(), true);
    }

    @Transactional
    public ShopLikeResponseDto unlike(ShopLikeRequestDto shopLikeRequestDto) {
        Member member = memberService.findById(shopLikeRequestDto.getUserId());
        Shop shop = findById(shopLikeRequestDto.getShopId());
        shop.unlike(member);
        return new ShopLikeResponseDto(shop.getLikeCount(), false);
    }

    private void updateContent(ShopRequestDto shopRequestDto, Shop shop) {
        ShopContent content = new ShopContent(
                shopRequestDto.getName(),
                shopRequestDto.getStart(),
                shopRequestDto.getEnd(),
                shopRequestDto.getPhone()
        );

        shop.update(content, shopRequestDto.getAddress());
    }

    private void updateCategory(ShopRequestDto shopRequestDto, Shop shop, Category category) {
        List<Category> subCategories = categoryService.findAllContainIds(shopRequestDto.getSubCategories());
        shop.updateCategories(category, subCategories);
    }

    private void updateTag(ShopRequestDto shopRequestDto, Shop shop) {
        List<Tag> tags = tagService.findAllContainIds(shopRequestDto.getTags());
        shop.updateTags(tags);
    }

    private void updateImage(ShopRequestDto shopRequestDto, Shop shop) {
        ShopImages shopImages = changeImages(shop, shopRequestDto);
        shop.updateImage(shopImages);
    }

    private ShopImages changeImages(Shop shop, ShopRequestDto shopRequestDto) {
        imageUploader.removeAll(shop.getShopImages());
        shopImageRepository.deleteById(shop.getId());
        return saveImages(shopRequestDto.getImages());
    }

    @Transactional(readOnly = true)
    public List<ShopSimpleResponseDto> findLikeShops(Long memberId, Long categoryId, Pageable pageable) {

        Category category = categoryService.findById(categoryId);
        Member member = memberService.findById(memberId);


        Page<Shop> shops = shopRepository.findByCategoryAndLikesMember(category, member, pageable);
        return shops.get()
                    .map(ShopSimpleResponseDto::from)
                    .collect(Collectors.toList());

    }


    @Transactional(readOnly = true)
    public boolean isLike(Long id, LoginMember loginMember) {
        Shop shop = findById(id);
        return shop.containLike(loginMember);
    }

    public void createNaverShops(String keyword, int displayCount, int page) {
        List<Shop> shops = new ArrayList<>();

        for (String name : TYPES) {
            Category category = categoryService.findByName(name);
            NaverShopDto naverShop = shopInserter.getShop(keyword, name, page, displayCount);
            naverShop.setBasicData(category, keyword);
            List<Shop> shop = toShop(naverShop);
            shops.addAll(shop);
        }

        List<Shop> results = new ArrayList<>();
        for (Shop newShop : shops) {
            Optional<Shop> shop = shopRepository.findByNaverId(newShop.getNaverId());

            if (shop.isEmpty()) {
                results.add(newShop);
                continue;
            }

            Shop existingShop = shop.get();
            List<Tag> tags = newShop.getTags();
            List<Category> categories = newShop.getSubcategories();
            existingShop.updateTags(tags);
            existingShop.updateCategories(existingShop.getCategory(), categories);
        }

        shopRepository.saveAll(results);
    }

    private List<Shop> toShop(NaverShopDto naverShop) {
        return naverShop.getAllShops().stream()
                        .filter(dto -> validatesEmptyList(dto.getShortAddress()))
                        .filter(dto -> containKeyword(dto.getShortAddress(), "포항"))
                        .map(dto -> {
                            Category category = dto.getMainCategory();
                            List<Category> subCategories = categoryService.getSubCategories(category, dto.getCategory());
                            List<Tag> tags = tagService.getTags(dto.getTagCandidates());
                            ShopImages shopImages = new ShopImages(List.of(new ShopImage(dto.getThumUrl(), true)));

                            ShopServiceAdapter adapter = shopAdapterService.shopAdapterByCategory(category);
                            Shop shop = adapter.createNaverShop(shopImages, dto);

                            shop.addTags(tags);
                            shop.addCategories(category, subCategories);

                            return shop;
                        }).collect(Collectors.toList());
    }

    private boolean containKeyword(List<String> address, String keyword) {
        String basicAddress = address.get(0);
        return basicAddress.contains(keyword);
    }
}
