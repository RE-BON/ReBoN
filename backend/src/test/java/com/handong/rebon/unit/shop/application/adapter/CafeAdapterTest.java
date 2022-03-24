package com.handong.rebon.unit.shop.application.adapter;

import java.util.ArrayList;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.shop.application.MenuGroupService;
import com.handong.rebon.shop.application.adapter.CafeServiceAdapter;
import com.handong.rebon.shop.application.dto.ShopRequestDto;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.type.Cafe;

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
class CafeAdapterTest {

    @Mock
    private MenuGroupService menuGroupService;

    @Test
    @DisplayName("카페 카테고리인 경우 지원한다.")
    void supportCategory() {
        // given
        CafeServiceAdapter adapter = new CafeServiceAdapter(menuGroupService);
        Category category = new Category("카페");

        // when
        boolean supports = adapter.supports(category);

        // then
        assertThat(supports).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"식당", "숙소", "명소"})
    @DisplayName("카페 카테고리가 아닌 경우 지원하지 않는다.")
    void notSupportCategory(String name) {
        // given
        CafeServiceAdapter adapter = new CafeServiceAdapter(menuGroupService);
        Category category = new Category(name);

        // when
        boolean supports = adapter.supports(category);

        // then
        assertThat(supports).isFalse();
    }

    @Test
    @DisplayName("Cafe를 생성한다")
    void create() {
        // given
        CafeServiceAdapter adapter = new CafeServiceAdapter(menuGroupService);
        ShopImages shopImages = new ShopImages();
        ShopRequestDto data = ShopRequestDto.builder()
                                            .name("스타벅스")
                                            .build();
        given(menuGroupService.createMenu(any(), any())).willReturn(new ArrayList<>());

        // when
        Shop shop = adapter.create(shopImages, data);

        // then
        assertThat(shop).isInstanceOf(Cafe.class);
    }
}
