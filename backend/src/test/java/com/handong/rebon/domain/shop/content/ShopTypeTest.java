package com.handong.rebon.domain.shop.content;

import java.util.ArrayList;

import com.handong.rebon.exception.shop.NoSuchCategoryException;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.type.ShopType;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.type.Cafe;
import com.handong.rebon.shop.domain.type.Lodging;
import com.handong.rebon.shop.domain.type.Restaurant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ShopTypeTest {
    private static String NAME = "name";
    private static String BUSINESS_HOUR = "10:00 - 22:00";
    private static String PHONE = "010-1234-5678";
    private static String ADDRESS = "경상북도 포항";
    private static String LONGITUDE = "129.4";
    private static String LATITUDE = "36.1";

    @ParameterizedTest
    @ValueSource(strings = {"식당", "카페", "숙소"})
    @DisplayName("카테고리에 따라 알맞은 ShopType을 가져온다")
    void getShopType(String name) {
        // given
        Category category = new Category(name);

        // when
        ShopType type = ShopType.findByCategory(category);

        // then
        assertThat(type.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("존재하지 않는 카테고리인 경우")
    void notExistCategory() {
        // given
        Category category = new Category("관광명소");

        // when & then
        assertThatThrownBy(() -> ShopType.findByCategory(category))
                .isInstanceOf(NoSuchCategoryException.class);
    }

    @Test
    @DisplayName("카테고리가 식당인 경우 Restaurant 인스턴스가 생성된다.")
    void createRestaurant() {
        // given
        Category category = new Category("식당");

        // when
        ShopType type = ShopType.findByCategory(category);
        Shop shop = type.create(category, NAME, BUSINESS_HOUR, PHONE, ADDRESS, LONGITUDE, LATITUDE, new ShopImages(), new ArrayList<>());

        // then
        assertThat(shop).isInstanceOf(Restaurant.class);
    }

    @Test
    @DisplayName("카테고리가 카페인 경우 Cafe 인스턴스가 생성된다.")
    void createCafe() {
        // given
        Category category = new Category("카페");

        // when
        ShopType type = ShopType.findByCategory(category);
        Shop shop = type.create(category, NAME, BUSINESS_HOUR, PHONE, ADDRESS, LONGITUDE, LATITUDE, new ShopImages(), new ArrayList<>());

        // then
        assertThat(shop).isInstanceOf(Cafe.class);
    }

    @Test
    @DisplayName("카테고리가 숙소인 경우 Lodging 인스턴스가 생성된다.")
    void createLodging() {
        // given
        Category category = new Category("숙소");

        // when
        ShopType type = ShopType.findByCategory(category);
        Shop shop = type.create(category, NAME, BUSINESS_HOUR, PHONE, ADDRESS, LONGITUDE, LATITUDE, new ShopImages(), new ArrayList<>());

        // then
        assertThat(shop).isInstanceOf(Lodging.class);
    }
}
