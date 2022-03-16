package com.handong.rebon.unit.shop.domain.adapter;

import com.handong.rebon.shop.domain.adapter.CafeAdapter;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.ShopData;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.type.Cafe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class CafeAdapterTest {

    @Test
    @DisplayName("카페 카테고리인 경우 지원한다.")
    void supportCategory() {
        // given
        CafeAdapter adapter = new CafeAdapter();
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
        CafeAdapter adapter = new CafeAdapter();
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
        CafeAdapter adapter = new CafeAdapter();
        ShopData shopData = new ShopData();

        // when
        Shop shop = adapter.create(shopData);

        // then
        assertThat(shop).isInstanceOf(Cafe.class);
    }
}
