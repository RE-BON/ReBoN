package com.handong.rebon.integration.shop;

import java.util.*;
import java.util.stream.Collectors;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.category.domain.repository.CategoryRepository;
import com.handong.rebon.common.admin.AdminCategoryRegister;
import com.handong.rebon.common.admin.AdminShopRegister;
import com.handong.rebon.common.admin.AdminTagRegister;
import com.handong.rebon.common.factory.ImageFactory;
import com.handong.rebon.integration.IntegrationTest;
import com.handong.rebon.shop.application.ShopService;
import com.handong.rebon.shop.application.dto.request.ShopCreateRequestDto;
import com.handong.rebon.shop.application.dto.request.menu.MenuGroupRequestDto;
import com.handong.rebon.shop.application.dto.request.menu.MenuRequestDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.repository.ShopRepository;
import com.handong.rebon.shop.domain.type.Restaurant;
import com.handong.rebon.tag.domain.Tag;

import com.handong.rebon.tag.domain.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ShopIntegrationTest extends IntegrationTest {

    @Autowired
    protected ShopService shopService;

    @Autowired
    protected ShopRepository shopRepository;

    @Autowired
    protected CategoryRepository categoryRepository;

    @Autowired
    protected TagRepository tagRepository;

    @Autowired
    private AdminTagRegister adminTagRegister;

    @Autowired
    private AdminCategoryRegister adminCategoryRegister;

    @Autowired
    private AdminShopRegister adminShopRegister;

    private Map<String, Tag> tags = new HashMap<>();
    private Map<String, Category> categories = new HashMap<>();

    @BeforeEach
    void setUp() {
        tags = adminTagRegister.register("포항", "영일대");
        categories = adminCategoryRegister.registerMain("식당", "숙소", "카페");
        categories.putAll(adminCategoryRegister.registerSubs(categories.get("식당"), "한식", "분식"));
        categories.putAll(adminCategoryRegister.registerSubs(categories.get("카페"), "프랜차이즈", "개인카페"));
    }

    @Test
    @DisplayName("단일 가게 생성")
    void createOne() {
        // given
        ShopCreateRequestDto shopCreateRequestDto = getShopCreateRequestDto(
                categories.get("식당"),
                List.of(categories.get("한식"), categories.get("분식")),
                List.of(tags.get("포항"), tags.get("영일대"))
        );

        // when
        Long id = shopService.create(shopCreateRequestDto);
        Restaurant restaurant = (Restaurant) shopRepository.getById(id);

        // then
        assertThat(id).isNotNull();
        assertThat(restaurant.getShopContent().getName()).isEqualTo("팜스발리");
        assertThat(restaurant.getMenus()).hasSize(4);
        assertThat(restaurant.getMenus().get(0).getMenuGroup()).isNotNull();
        assertThat(restaurant.getShopTags()).hasSize(2);
        assertThat(restaurant.getCategory().getName()).isEqualTo("식당");
        assertThat(restaurant.getShopCategories()).hasSize(2);
        assertThat(restaurant.getMainImage()).contains("정면사진");
    }

    @Test
    @DisplayName("가게 삭제 - soft delete")
    void deleteShop() {
        // given
        Shop shop = adminShopRegister.CafeRegisterWithMenu(
                "스타벅스",
                categories.get("카페"),
                List.of(categories.get("프랜차이즈")),
                List.of(tags.get("포항"))
        );

        Long savedShopId = shop.getId();
        boolean beforeDelete = shop.isDeleted();

        // when
        shopService.delete(savedShopId);
        Optional<Shop> afterDelete = shopRepository.findById(savedShopId);

        // then
        assertThat(beforeDelete).isFalse();
        assertThat(afterDelete).isEmpty();
    }

    private ShopCreateRequestDto getShopCreateRequestDto(Category category, List<Category> subs, List<Tag> tags) {
        List<Long> subIds = subs.stream().map(Category::getId).collect(Collectors.toList());
        List<Long> tagIds = tags.stream().map(Tag::getId).collect(Collectors.toList());

        // TODO 나중에 로그인 된 유저만 할 수 있는지도 검증해야함(인터셉터)
        return ShopCreateRequestDto.builder()
                                   .categoryId(category.getId())
                                   .subCategories(subIds)
                                   .name("팜스발리")
                                   .businessHour("10:00 ~ 22:00")
                                   .phone("010-1234-5678")
                                   .address("경상북도 포항")
                                   .longitude("129.389762")
                                   .latitude("36.102440")
                                   .tags(tagIds)
                                   .images(getImages())
                                   .menus(getMenus())
                                   .build();
    }

    private List<MenuGroupRequestDto> getMenus() {
        return List.of(
                new MenuGroupRequestDto("피자메뉴", Arrays.asList(
                        new MenuRequestDto("치즈 피자", 15000),
                        new MenuRequestDto("페퍼로니 피자", 16000)
                )),
                new MenuGroupRequestDto("파스타메뉴", Arrays.asList(
                        new MenuRequestDto("토마토 파스타", 13000),
                        new MenuRequestDto("크림 파스타", 13000)
                ))
        );
    }

    private List<MultipartFile> getImages() {
        return List.of(
                ImageFactory.createFakeImage("정면사진"),
                ImageFactory.createFakeImage("내부사진1"),
                ImageFactory.createFakeImage("내부사진2")
        );
    }
}
