package com.handong.rebon.integration.shop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.handong.rebon.menu.presentation.dto.request.MenuGroupRequest;
import com.handong.rebon.menu.presentation.dto.request.MenuRequest;
import com.handong.rebon.shop.application.ShopService;
import com.handong.rebon.shop.application.dto.ShopRequestDto;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.category.CategoryRepository;
import com.handong.rebon.shop.domain.repository.ShopRepository;
import com.handong.rebon.shop.domain.tag.Tag;
import com.handong.rebon.shop.domain.tag.TagRepository;
import com.handong.rebon.shop.domain.type.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ShopIntegrationTest {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @Test
    @DisplayName("단일 가게 생성")
    void createOne() {
        // given
        Map<String, Long> categories = createCategory();
        Map<String, Long> tags = createTag();

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
        ShopRequestDto shopRequestDto = ShopRequestDto.builder()
                                                      .categoryId(categories.get("식당"))
                                                      .subCategories(Arrays.asList(categories.get("한식"), categories.get("분식")))
                                                      .name("팜스발리")
                                                      .businessHour("10:00 ~ 22:00")
                                                      .phone("010-1234-5678")
                                                      .address("경상북도 포항")
                                                      .longitude("129.389762")
                                                      .latitude("36.102440")
                                                      .tags(Arrays.asList(tags.get("포항"), tags.get("한동대")))
                                                      //.images() TODO 이미지 저장 구현되면 추가
                                                      .menus(menuGroupRequests)
                                                      .build();

        // when
        Long id = shopService.create(shopRequestDto);
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

    private Map<String, Long> createCategory() {
        Map<String, Long> categories = new HashMap<>();
        Category 식당 = categoryRepository.save(new Category("식당"));
        Category 한식 = categoryRepository.save(new Category("한식"));
        Category 분식 = categoryRepository.save(new Category("분식"));

        categories.put(식당.getName(), 식당.getId());
        categories.put(한식.getName(), 한식.getId());
        categories.put(분식.getName(), 분식.getId());

        return categories;
    }

    private Map<String, Long> createTag() {
        Map<String, Long> tags = new HashMap<>();
        Tag 포항 = tagRepository.save(new Tag("포항"));
        Tag 한동대 = tagRepository.save(new Tag("한동대"));

        tags.put(포항.getName(), 포항.getId());
        tags.put(한동대.getName(), 한동대.getId());

        return tags;
    }
}
