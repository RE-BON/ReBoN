package com.handong.rebon.integration.shop;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.category.domain.repository.CategoryRepository;
import com.handong.rebon.common.factory.ImageFactory;
import com.handong.rebon.integration.IntegrationTest;
import com.handong.rebon.shop.application.ShopService;
import com.handong.rebon.shop.application.dto.request.ShopCreateRequestDto;
import com.handong.rebon.shop.application.dto.request.menu.MenuGroupRequestDto;
import com.handong.rebon.shop.application.dto.request.menu.MenuRequestDto;
import com.handong.rebon.shop.domain.repository.ShopRepository;
import com.handong.rebon.shop.domain.type.Restaurant;
import com.handong.rebon.tag.domain.Tag;

import com.handong.rebon.tag.domain.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

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
    void createOne() throws IOException {
        // given
        Category 식당 = createCategory("식당");
        Category 한식 = createCategory("한식");
        Category 분식 = createCategory("분식");

        Tag 포항 = createTag("포항");
        Tag 영일대 = createTag("영일대");

        // TODO 나중에 로그인 된 유저만 할 수 있는지도 검증해야함(인터셉터)
        ShopCreateRequestDto shopCreateRequestDto = ShopCreateRequestDto.builder()
                                                                        .categoryId(식당.getId())
                                                                        .subCategories(List.of(한식.getId(), 분식.getId()))
                                                                        .name("팜스발리")
                                                                        .businessHour("10:00 ~ 22:00")
                                                                        .phone("010-1234-5678")
                                                                        .address("경상북도 포항")
                                                                        .longitude("129.389762")
                                                                        .latitude("36.102440")
                                                                        .tags(List.of(포항.getId(), 영일대.getId()))
                                                                        .images(getImages())
                                                                        .menus(getMenus())
                                                                        .build();

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

    protected Category createCategory(String name) {
        Category category = new Category(name);
        return categoryRepository.save(category);
    }

    protected Tag createTag(String name) {
        Tag tag = new Tag(name);
        return tagRepository.save(tag);
    }
}
