package com.handong.rebon.unit.shop.domain.menu;

import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.menu.Menu;
import com.handong.rebon.shop.domain.menu.MenuGroup;
import com.handong.rebon.shop.domain.type.Restaurant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MenuTest {

    @Test
    @DisplayName("메뉴가 특정 가게에 속한다.")
    void belongShop() {
        // given
        ShopContent content = ShopContent.builder()
                                         .name("팜스발리")
                                         .build();

        Restaurant restaurant = Restaurant.builder()
                                          .shopContent(content)
                                          .shopImages(new ShopImages())
                                          .build();

        Menu menu = new Menu("치즈 피자", 15000);

        // when
        menu.belongShop(restaurant);

        // then
        assertThat(menu.getShop()).isNotNull();
        assertThat(menu.getShop()).extracting("shopContent")
                                  .extracting("name")
                                  .isEqualTo("팜스발리");
    }

    @Test
    @DisplayName("메뉴가 특정 메뉴 그룹에 속한다.")
    void belongMenuGroup() {
        // given
        Menu menu = new Menu("치즈 피자", 15000);
        MenuGroup menuGroup = new MenuGroup("피자메뉴");

        // when
        menu.belongTo(menuGroup);

        // then
        assertThat(menu.getMenuGroup()).isNotNull();
        assertThat(menu.getMenuGroup()).extracting("name")
                                       .isEqualTo("피자메뉴");
    }
}
