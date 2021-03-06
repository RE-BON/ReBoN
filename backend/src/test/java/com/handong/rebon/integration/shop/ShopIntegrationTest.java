package com.handong.rebon.integration.shop;

import java.time.LocalTime;
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
import com.handong.rebon.shop.application.dto.request.ShopRequestDto;
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
        tags = adminTagRegister.register("??????", "?????????");
        categories = adminCategoryRegister.registerMain("??????", "??????", "??????");
        categories.putAll(adminCategoryRegister.registerSubs(categories.get("??????"), "??????", "??????"));
        categories.putAll(adminCategoryRegister.registerSubs(categories.get("??????"), "???????????????", "????????????"));
    }

    @Test
    @DisplayName("?????? ?????? ??????")
    void createOne() {
        // given
        ShopRequestDto shopRequestDto = getShopCreateRequestDto(
                categories.get("??????"),
                List.of(categories.get("??????"), categories.get("??????")),
                List.of(tags.get("??????"), tags.get("?????????"))
        );

        // when
        Long id = shopService.create(shopRequestDto);
        Restaurant restaurant = (Restaurant) shopRepository.getById(id);

        // then
        assertThat(id).isNotNull();
        assertThat(restaurant.getShopContent().getName()).isEqualTo("????????????");
        assertThat(restaurant.getMenus()).hasSize(4);
        assertThat(restaurant.getMenus().get(0).getMenuGroup()).isNotNull();
        assertThat(restaurant.getShopTags()).hasSize(2);
        assertThat(restaurant.getCategory().getName()).isEqualTo("??????");
        assertThat(restaurant.getShopCategories()).hasSize(2);
        assertThat(restaurant.getMainImage()).contains("????????????");
    }

    @Test
    @DisplayName("?????? ?????? - soft delete")
    void deleteShop() {
        // given
        Shop shop = adminShopRegister.CafeRegisterWithMenu(
                "????????????",
                categories.get("??????"),
                List.of(categories.get("???????????????")),
                List.of(tags.get("??????")),
                null,
                null
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

    @Test
    @DisplayName("?????? ?????? - ????????? ??????????????? ?????? ??????")
    void shopUpdateSameCategory() {
        // given
        Shop shop = adminShopRegister.CafeRegisterWithMenu(
                "????????????",
                categories.get("??????"),
                List.of(categories.get("???????????????")),
                List.of(tags.get("??????")),
                null,
                null
        );

        ShopRequestDto shopRequestDto = getShopCreateRequestDto(
                categories.get("??????"),
                List.of(categories.get("????????????")),
                List.of(tags.get("??????"), tags.get("?????????"))
        );

        // when
        Long updatedShopId = shopService.update(shop.getId(), shopRequestDto);
        Shop updatedShop = shopRepository.getById(updatedShopId);

        // then
        assertThat(updatedShop.getCategory().getName()).isEqualTo("??????");
        assertThat(updatedShop.getShopCategories()).hasSize(1);
        assertThat(updatedShop.getShopCategories())
                .extracting("category")
                .extracting("name")
                .containsOnly("????????????");
        assertThat(updatedShop.getShopTags()).hasSize(2);
        assertThat(updatedShop.getShopTags())
                .extracting("tag")
                .extracting("name")
                .containsOnly("??????", "?????????");
    }

    @Test
    @DisplayName("?????? ?????? - ????????? ???????????? ????????? ????????? ??????")
    void shopUpdateOtherCategory() {
        // given
        Shop shop = adminShopRegister.CafeRegisterWithMenu(
                "????????????",
                categories.get("??????"),
                List.of(categories.get("???????????????")),
                List.of(tags.get("??????")),
                null,
                null
        );

        ShopRequestDto shopRequestDto = getShopCreateRequestDto(
                categories.get("??????"),
                List.of(categories.get("??????"), categories.get("??????")),
                List.of(tags.get("??????"), tags.get("?????????"))
        );

        // when
        Long updatedShopId = shopService.update(shop.getId(), shopRequestDto);
        Shop updatedShop = shopRepository.getById(updatedShopId);

        // then
        assertThat(updatedShop.getCategory().getName()).isEqualTo("??????");
        assertThat(updatedShop.getShopCategories()).hasSize(2);
        assertThat(updatedShop.getShopCategories())
                .extracting("category")
                .extracting("name")
                .containsOnly("??????", "??????");
        assertThat(updatedShop.getShopTags()).hasSize(2);
        assertThat(updatedShop.getShopTags())
                .extracting("tag")
                .extracting("name")
                .containsOnly("??????", "?????????");
    }

    private ShopRequestDto getShopCreateRequestDto(Category category, List<Category> subs, List<Tag> tags) {
        List<Long> subIds = subs.stream().map(Category::getId).collect(Collectors.toList());
        List<Long> tagIds = tags.stream().map(Tag::getId).collect(Collectors.toList());

        // TODO ????????? ????????? ??? ????????? ??? ??? ???????????? ???????????????(????????????)
        return ShopRequestDto.builder()
                             .categoryId(category.getId())
                             .subCategories(subIds)
                             .name("????????????")
                             .start(LocalTime.of(10, 0))
                             .end(LocalTime.of(22, 0))
                             .phone("010-1234-5678")
                             .address("???????????? ??????")
                             .tags(tagIds)
                             .images(getImages())
                             .menus(getMenus())
                             .build();
    }

    private List<MenuGroupRequestDto> getMenus() {
        return List.of(
                new MenuGroupRequestDto("????????????", Arrays.asList(
                        new MenuRequestDto("?????? ??????", 15000),
                        new MenuRequestDto("???????????? ??????", 16000)
                )),
                new MenuGroupRequestDto("???????????????", Arrays.asList(
                        new MenuRequestDto("????????? ?????????", 13000),
                        new MenuRequestDto("?????? ?????????", 13000)
                ))
        );
    }

    private List<MultipartFile> getImages() {
        return List.of(
                ImageFactory.createFakeImage("????????????"),
                ImageFactory.createFakeImage("????????????1"),
                ImageFactory.createFakeImage("????????????2")
        );
    }
}
