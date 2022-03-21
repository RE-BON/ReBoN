package com.handong.rebon.unit.shop.domain.type;

import java.util.Arrays;
import java.util.List;

import com.handong.rebon.menu.domain.Menu;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.type.Restaurant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RestaurantTest {

    @Test
    @DisplayName("메뉴를 추가한다.")
    void addMenu() {
        // given
        ShopContent content = ShopContent.builder()
                                         .name("팜스발리")
                                         .build();

        Restaurant restaurant = Restaurant.builder()
                                          .shopContent(content)
                                          .shopImages(new ShopImages())
                                          .build();

        List<Menu> menus = Arrays.asList(new Menu("치즈피자", 15000), new Menu("토마토파스타", 12000));

        // when
        restaurant.addMenu(menus);

        // then
        assertThat(restaurant.getMenus()).hasSize(2);
        assertThat(restaurant.getMenus().get(0).getShop()).isEqualTo(restaurant);
    }
}
