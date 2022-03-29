package com.handong.rebon.integration.shop;

import java.util.Arrays;
import java.util.List;

import com.handong.rebon.category.Category;
import com.handong.rebon.category.CategoryRepository;
import com.handong.rebon.integration.IntegrationTest;
import com.handong.rebon.shop.application.ShopService;
import com.handong.rebon.shop.application.dto.request.ShopCreateRequestDto;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.repository.ShopRepository;
import com.handong.rebon.shop.domain.type.Cafe;
import com.handong.rebon.shop.domain.type.Lodging;
import com.handong.rebon.shop.domain.type.Restaurant;
import com.handong.rebon.shop.presentation.dto.request.MenuGroupRequest;
import com.handong.rebon.shop.presentation.dto.request.MenuRequest;
import com.handong.rebon.tag.domain.Tag;
import com.handong.rebon.tag.domain.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;

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

    @Test
    @DisplayName("단일 가게 생성")
    void createOne() {
        // given
        Category 식당 = createCategory("식당");
        Category 한식 = createCategory("한식");
        Category 분식 = createCategory("분식");

        Tag 포항 = createTag("포항");
        Tag 영일대 = createTag("영일대");

        List<MenuGroupRequest> menuGroupRequests = Arrays.asList(
                new MenuGroupRequest("피자메뉴", Arrays.asList(
                        new MenuRequest("치즈 피자", 15000),
                        new MenuRequest("페퍼로니 피자", 16000)
                )),
                new MenuGroupRequest("파스타메뉴", Arrays.asList(
                        new MenuRequest("토마토 파스타", 13000),
                        new MenuRequest("크림 파스타", 13000)
                ))
        );

        // TODO 나중에 로그인 된 유저만 할 수 있는지도 검증해야함
        ShopCreateRequestDto shopCreateRequestDto = ShopCreateRequestDto.builder()
                                                                        .categoryId(식당.getId())
                                                                        .subCategories(Arrays.asList(한식.getId(), 분식.getId()))
                                                                        .name("팜스발리")
                                                                        .businessHour("10:00 ~ 22:00")
                                                                        .phone("010-1234-5678")
                                                                        .address("경상북도 포항")
                                                                        .longitude("129.389762")
                                                                        .latitude("36.102440")
                                                                        .tags(Arrays.asList(포항.getId(), 영일대.getId()))
                                                                        //.images() TODO 이미지 저장 구현되면 추가
                                                                        .menus(menuGroupRequests)
                                                                        .build();

        // when
        Long id = shopService.create(shopCreateRequestDto);
        Restaurant restaurant = (Restaurant) shopRepository.findById(id).get();

        // then
        assertThat(id).isNotNull();
        assertThat(restaurant.getShopContent().getName()).isEqualTo("팜스발리");
        assertThat(restaurant.getMenus()).hasSize(4);
        assertThat(restaurant.getMenus().get(0).getMenuGroup()).isNotNull();
        assertThat(restaurant.getShopTags()).hasSize(2);
        assertThat(restaurant.getCategory().getName()).isEqualTo("식당");
        assertThat(restaurant.getShopCategories()).hasSize(2);
    }

    protected Restaurant createRestaurant(Category category, String name) {
        Restaurant restaurant = Restaurant.builder()
                                          .category(category)
                                          .shopContent(new ShopContent(name))
                                          .shopScore(new ShopScore(4.8, 100))
                                          .shopImages(new ShopImages())
                                          .build();
        return shopRepository.save(restaurant);
    }

    protected Restaurant createRestaurant(Category category, List<Tag> tags, String name) {
        Restaurant restaurant = Restaurant.builder()
                                          .category(category)
                                          .shopContent(new ShopContent(name))
                                          .shopScore(new ShopScore(4.8, 100))
                                          .shopImages(new ShopImages())
                                          .build();
        restaurant.addTags(tags);
        return shopRepository.save(restaurant);
    }

    protected Cafe createCafe(Category category, String name) {
        Cafe cafe = Cafe.builder()
                        .category(category)
                        .shopContent(new ShopContent(name))
                        .shopScore(new ShopScore(4.8, 100))
                        .shopImages(new ShopImages())
                        .build();
        return shopRepository.save(cafe);
    }

    protected Cafe createCafe(Category category, List<Tag> tags, String name) {
        Cafe cafe = Cafe.builder()
                        .category(category)
                        .shopContent(new ShopContent(name))
                        .shopScore(new ShopScore(4.8, 100))
                        .shopImages(new ShopImages())
                        .build();
        cafe.addTags(tags);
        return shopRepository.save(cafe);
    }

    protected Lodging createLodging(Category category, String name) {
        Lodging lodging = Lodging.builder()
                                 .category(category)
                                 .shopContent(new ShopContent(name))
                                 .shopScore(new ShopScore(4.8, 100))
                                 .shopImages(new ShopImages())
                                 .build();
        return shopRepository.save(lodging);
    }

    protected Category createCategory(String name) {
        Category category = new Category(name);
        return categoryRepository.save(category);
    }

    protected Tag createTag(String name) {
        Tag tag = new Tag(name);
        return tagRepository.save(tag);
    }
}
