package com.handong.rebon.unit.shop.domain.type;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.menu.Menu;
import com.handong.rebon.shop.domain.menu.MenuGroup;
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

    @Test
    @DisplayName("식당에 속한 메뉴를 그룹화한다.")
    void menuGroupByMenuGroup() {
        // given
        MenuGroup 식사 = new MenuGroup("식사");
        MenuGroup 음료 = new MenuGroup("음료");

        List<Menu> menus = Arrays.asList(
                new Menu("치즈피자", 15000, 식사),
                new Menu("토마토파스타", 12000, 식사),
                new Menu("사이다", 2000, 음료),
                new Menu("콜라", 2000, 음료)
        );
        Restaurant restaurant = Restaurant.builder()
                                          .shopContent(new ShopContent("팜스발리"))
                                          .shopImages(new ShopImages())
                                          .build();

        restaurant.addMenu(menus);

        // when
        Map<MenuGroup, List<Menu>> grouping = restaurant.getMenuGroupByMenuGroup();

        // then
        assertThat(grouping.get(식사)).hasSize(2);
        assertThat(grouping.get(식사)).extracting("name")
                                    .containsOnly("치즈피자", "토마토파스타");
        assertThat(grouping.get(음료)).hasSize(2);
        assertThat(grouping.get(음료)).extracting("name")
                                    .containsOnly("사이다", "콜라");
    }
}
