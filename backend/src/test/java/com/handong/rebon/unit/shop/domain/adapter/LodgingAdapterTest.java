package com.handong.rebon.unit.shop.domain.adapter;

import com.handong.rebon.shop.domain.adapter.LodgingAdapter;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.ShopData;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.type.Lodging;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class LodgingAdapterTest {

    @Test
    @DisplayName("숙소 카테고리인 경우 지원한다.")
    void supportCategory() {
        // given
        LodgingAdapter adapter = new LodgingAdapter();
        Category category = new Category("숙소");

        // when
        boolean supports = adapter.supports(category);

        // then
        assertThat(supports).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"식당", "카페", "명소"})
    @DisplayName("숙소 카테고리가 아닌 경우 지원하지 않는다.")
    void notSupportCategory(String name) {
        // given
        LodgingAdapter adapter = new LodgingAdapter();
        Category category = new Category(name);

        // when
        boolean supports = adapter.supports(category);

        // then
        assertThat(supports).isFalse();
    }

    @Test
    @DisplayName("숙소를 생성한다")
    void create() {
        // given
        LodgingAdapter adapter = new LodgingAdapter();
        ShopData shopData = new ShopData();

        // when
        Shop shop = adapter.create(shopData);

        // then
        assertThat(shop).isInstanceOf(Lodging.class);
    }
}
