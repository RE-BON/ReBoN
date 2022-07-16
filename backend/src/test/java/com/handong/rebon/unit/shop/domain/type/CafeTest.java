package com.handong.rebon.unit.shop.domain.type;

import java.util.Arrays;
import java.util.List;

import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.menu.Menu;
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

}
