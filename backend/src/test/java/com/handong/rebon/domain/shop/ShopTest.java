package com.handong.rebon.domain.shop;

import java.util.List;

import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.tag.ShopTag;
import com.handong.rebon.shop.domain.tag.Tag;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ShopTest {

    @Test
    @DisplayName("위치 태그를 등록한다")
    void create() {
        // given
        ShopContent content = ShopContent.builder()
                                         .name("팜스발리")
                                         .build();

        Shop shop = Shop.builder()
                        .shopContent(content)
                        .build();

        List<Tag> tags = List.of(
                new Tag("포항"),
                new Tag("한동대")
        );

        // when
        shop.addTags(tags);
        List<ShopTag> shopTags = shop.getShopTags();

        // then
        assertThat(shopTags).hasSize(2);
        assertThat(shopTags).extracting("tag")
                            .extracting("name")
                            .contains("포항", "한동대");
    }
}
