package com.handong.rebon.shop.application;

import java.util.List;
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
import com.handong.rebon.shop.domain.location.Location;
import com.handong.rebon.shop.domain.repository.ShopImageRepository;
import com.handong.rebon.shop.domain.repository.ShopRepository;
import com.handong.rebon.tag.application.TagService;
import com.handong.rebon.tag.domain.Tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final MemberService memberService;
    private final ShopRepository shopRepository;
    private final ImageUploader imageUploader;
    private final ShopImageRepository shopImageRepository;

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

        Location location = new Location(
                shopRequestDto.getAddress(),
                shopRequestDto.getLongitude(),
                shopRequestDto.getLatitude()
        );

        shop.update(content, location);
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

        long skipSize = (long) pageable.getPageNumber() * pageable.getPageSize();

        return member.filterByCategory(category)
                     .stream()
                     .skip(skipSize)
                     .limit(pageable.getPageSize())
                     .map(ShopSimpleResponseDto::from)
                     .collect(Collectors.toList());

    }


    @Transactional(readOnly = true)
    public boolean isLike(Long id, LoginMember loginMember) {
        Shop shop = findById(id);
        return shop.containLike(loginMember);
    }
}
