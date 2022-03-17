package com.handong.rebon.unit.shop.application.adapter;

import java.util.ArrayList;

import com.handong.rebon.menu.application.MenuService;
import com.handong.rebon.shop.application.adapter.RestaurantServiceAdapter;
import com.handong.rebon.shop.application.dto.ShopRequestDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.type.Restaurant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RestaurantAdapterTest {

    @Mock
    private MenuService menuService;

    @Test
    @DisplayName("식당 카테고리인 경우 지원한다.")
    void supportCategory() {
        // given
        RestaurantServiceAdapter adapter = new RestaurantServiceAdapter(menuService);
        Category category = new Category("식당");

        // when
        boolean supports = adapter.supports(category);

        // then
        assertThat(supports).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"카페", "숙소", "명소"})
    @DisplayName("식당 카테고리가 아닌 경우 지원하지 않는다.")
    void notSupportCategory(String name) {
        // given
        RestaurantServiceAdapter adapter = new RestaurantServiceAdapter(menuService);
        Category category = new Category(name);

        // when
        boolean supports = adapter.supports(category);

        // then
        assertThat(supports).isFalse();
    }

    @Test
    @DisplayName("Restaurant를 생성한다")
    void create() {
        // given
        RestaurantServiceAdapter adapter = new RestaurantServiceAdapter(menuService);
        ShopImages shopImages = new ShopImages();
        ShopRequestDto data = ShopRequestDto.builder()
                                            .name("팜스발리")
                                            .build();
        given(menuService.createMenu(any())).willReturn(new ArrayList<>());

        // when
        Shop shop = adapter.create(shopImages, data);

        // then
        assertThat(shop).isInstanceOf(Restaurant.class);
    }
}
