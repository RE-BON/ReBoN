package com.handong.rebon.integration.shop;

import java.util.List;

import com.handong.rebon.category.Category;
import com.handong.rebon.shop.application.dto.request.ShopRequestDto;
import com.handong.rebon.shop.application.dto.response.ShopSimpleResponseDto;
import com.handong.rebon.shop.domain.type.Cafe;
import com.handong.rebon.shop.domain.type.Lodging;
import com.handong.rebon.shop.domain.type.Restaurant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ShopReadIntegrationTest extends ShopIntegrationTest {

    @Test
    @DisplayName("식당 카테고리의 모든 가게 목록을 가져온다.")
    void getRestaurants() {
        // given
        Category 식당 = createCategory("식당");
        Category 카페 = createCategory("카페");
        Restaurant 팜스발리 = createRestaurant(식당, "팜스발리");
        Restaurant 코리안테이블 = createRestaurant(식당, "코리안 테이블");
        createCafe(카페, "스타벅스");

        ShopRequestDto shopRequestDto = ShopRequestDto.builder()
                                                      .categoryId(식당.getId())
                                                      .build();

        // when
        List<ShopSimpleResponseDto> shops = shopService.findByCategory(shopRequestDto);

        // then
        assertThat(shops).hasSize(2);
        assertThat(shops).extracting("name")
                         .contains(팜스발리.getShopContent().getName(), 코리안테이블.getShopContent().getName());
    }

    @Test
    @DisplayName("카페 카테고리의 모든 가게 목록을 가져온다.")
    void getCafes() {
        // given
        Category 식당 = createCategory("식당");
        Category 카페 = createCategory("카페");
        createRestaurant(식당, "팜스발리");
        createRestaurant(식당, "코리안 테이블");
        Cafe 스타벅스 = createCafe(카페, "스타벅스");

        ShopRequestDto shopRequestDto = ShopRequestDto.builder()
                                                      .categoryId(카페.getId())
                                                      .build();

        // when
        List<ShopSimpleResponseDto> shops = shopService.findByCategory(shopRequestDto);

        // then
        assertThat(shops).hasSize(1);
        assertThat(shops).extracting("name")
                         .contains(스타벅스.getShopContent().getName());
    }

    @Test
    @DisplayName("숙소 카테고리의 모든 가게 목록을 가져온다.")
    void getLodging() {
        // given
        Category 숙소 = createCategory("숙소");
        Category 식당 = createCategory("식당");
        Category 카페 = createCategory("카페");
        Lodging 한동_게스트하우스 = createLodging(숙소, "한동 게스트하우스");
        Lodging 영일대_호텔 = createLodging(숙소, "영일대 호텔");
        createRestaurant(식당, "팜스발리");
        createCafe(카페, "스타벅스");

        ShopRequestDto shopRequestDto = ShopRequestDto.builder()
                                                      .categoryId(숙소.getId())
                                                      .build();

        // when
        List<ShopSimpleResponseDto> shops = shopService.findByCategory(shopRequestDto);

        // then
        assertThat(shops).hasSize(2);
        assertThat(shops).extracting("name")
                         .contains(한동_게스트하우스.getShopContent().getName(), 영일대_호텔.getShopContent().getName());
    }

    @Test
    @DisplayName("식당 카테고리의 한동대 태그를 가진 모든 가게 목록을 가져온다.")
    void getRestaurantsInHandong() {
        // given

        // when

        // then
    }

    @Test
    @DisplayName("식당 카테고리의 한동대 태그를 가진 팜스발리 이름을 포함한 가게 목록을 가져온다.")
    void getRestaurantsInHandongWithName() {
        // given

        // when

        // then
    }

    @Test
    @DisplayName("카페 카테고리의 스타벅스 이름을 포함한 가게 목록을 가져온다.")
    void getCafesWithName() {
        // given

        // when

        // then
    }

    @Test
    @DisplayName("카페 카테고리의 한동대와 영일대에 있는 카페 목록을 가져온다.")
    void getCafesInHandongAndYeongildae() {
        // given

        // when

        // then
    }

    @Test
    @DisplayName("카페 카테고리의 한동대와 영일대에 있는 스타벅스 목록을 가져온다.")
    void getCafesInHandongAndYeongildaeWithName() {
        // given

        // when

        // then
    }

}
