package com.handong.rebon.unit.menu.domain;

import com.handong.rebon.menu.domain.Menu;
import com.handong.rebon.menu.domain.MenuGroup;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.type.Restaurant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MenuGroupTest {

    @Test
    @DisplayName("메뉴 그룹에 메뉴를 추가한다.")
    void addMenu() {
        // given
        MenuGroup menuGroup = new MenuGroup("피자메뉴");
        Menu menu = new Menu("치즈 피자", 15000);

        // when
        menuGroup.addMenu(menu);

        // then
        assertThat(menuGroup.getMenus()).hasSize(1);
        assertThat(menu.getMenuGroup()).isNotNull();
    }

    @Test
    @DisplayName("메뉴그룹이 특정 가게에 속한다.")
    void belongShop() {
        // given
        ShopContent content = ShopContent.builder()
                                         .name("팜스발리")
                                         .build();

        Restaurant restaurant = Restaurant.builder()
                                          .shopContent(content)
                                          .shopImages(new ShopImages())
                                          .build();

        MenuGroup menuGroup = new MenuGroup("피자메뉴");

        // when
        menuGroup.belongTo(restaurant);

        // then
        assertThat(menuGroup.getShop()).isNotNull();
    }
}
