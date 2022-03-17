package com.handong.rebon.unit.shop.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.handong.rebon.exception.shop.ShopTagNumberException;
import com.handong.rebon.shop.domain.ShopCategory;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.tag.ShopTag;
import com.handong.rebon.shop.domain.tag.Tag;
import com.handong.rebon.shop.domain.type.Restaurant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ShopTest {

    @Test
    @DisplayName("위치 태그를 등록한다")
    void create() {
        // given
        ShopContent content = ShopContent.builder()
                                         .name("팜스발리")
                                         .build();

        Restaurant restaurant = Restaurant.builder()
                                          .shopContent(content)
                                          .build();

        List<Tag> tags = List.of(
                new Tag("포항"),
                new Tag("한동대")
        );

        // when
        restaurant.addTags(tags);
        List<ShopTag> shopTags = restaurant.getShopTags();

        // then
        assertThat(shopTags).hasSize(2);
        assertThat(shopTags).extracting("tag")
                            .extracting("name")
                            .contains("포항", "한동대");
    }

    @Test
    @DisplayName("태그가 없다면 가게를 생성할 수 없다.")
    void noTag() {
        // given
        ShopContent content = ShopContent.builder()
                                         .name("팜스발리")
                                         .build();

        Restaurant restaurant = Restaurant.builder()
                                          .shopContent(content)
                                          .build();

        List<Tag> tags = Collections.emptyList();

        // when & then
        assertThatThrownBy(() -> restaurant.addTags(tags))
                .isInstanceOf(ShopTagNumberException.class);
    }

    @Test
    @DisplayName("카테고리를 등록한다.")
    void addCategories() {
        // given
        Category parent = new Category("식당");
        List<Category> subCategories = Arrays.asList(
                new Category("한식"),
                new Category("분식")
        );

        ShopContent content = ShopContent.builder()
                                         .name("팜스발리")
                                         .build();

        Restaurant restaurant = Restaurant.builder()
                                          .shopContent(content)
                                          .build();

        // when
        restaurant.addCategories(parent, subCategories);
        Category parentCategory = restaurant.getCategory();
        List<ShopCategory> shopCategories = restaurant.getShopCategories();

        // then
        assertThat(parentCategory).extracting("name")
                                  .isEqualTo("식당");
        assertThat(shopCategories).hasSize(2);
        assertThat(shopCategories).extracting("category")
                                  .extracting("name")
                                  .contains("한식", "분식");
    }
}
