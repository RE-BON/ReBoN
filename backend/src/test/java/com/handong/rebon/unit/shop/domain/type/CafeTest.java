package com.handong.rebon.unit.shop.domain.type;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.menu.Menu;
import com.handong.rebon.shop.domain.menu.MenuGroup;
import com.handong.rebon.shop.domain.type.Cafe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CafeTest {

    @Test
    @DisplayName("메뉴를 추가한다.")
    void addMenu() {
        // given
        ShopContent content = ShopContent.builder()
                                         .name("스타벅스")
                                         .build();

        Cafe cafe = Cafe.builder()
                        .shopContent(content)
                        .shopImages(new ShopImages())
                        .build();

        List<Menu> menus = Arrays.asList(new Menu("아메리카노", 4000), new Menu("아이스티", 4000));

        // when
        cafe.addMenu(menus);

        // then
        assertThat(cafe.getMenus()).hasSize(2);
        assertThat(cafe.getMenus().get(0).getShop()).isEqualTo(cafe);
    }

    @Test
    @DisplayName("카페에 속한 메뉴를 그룹화한다.")
    void menuGroupByMenuGroup() {
        // given
        MenuGroup 커피류 = new MenuGroup("커피류");
        MenuGroup 디저트류 = new MenuGroup("디저트류");

        List<Menu> menus = Arrays.asList(
                new Menu("아메리카노", 5000, 커피류),
                new Menu("라떼", 6000, 커피류),
                new Menu("에스프레소", 4000, 커피류),
                new Menu("초코 케이크", 7000, 디저트류)
        );
        Cafe cafe = Cafe.builder()
                        .shopContent(new ShopContent("스타벅스"))
                        .shopImages(new ShopImages())
                        .build();

        cafe.addMenu(menus);

        // when
        Map<MenuGroup, List<Menu>> grouping = cafe.getMenuGroupByMenuGroup();

        // then
        assertThat(grouping.get(커피류)).hasSize(3);
        assertThat(grouping.get(커피류)).extracting("name")
                                     .containsOnly("아메리카노", "라떼", "에스프레소");
        assertThat(grouping.get(디저트류)).hasSize(1);
        assertThat(grouping.get(디저트류)).extracting("name")
                                      .containsOnly("초코 케이크");
    }
}
