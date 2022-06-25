package com.handong.rebon.integration.tag;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

import com.handong.rebon.exception.shop.ShopTagNumberException;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.repository.ShopRepository;
import com.handong.rebon.shop.domain.type.Restaurant;
import com.handong.rebon.tag.domain.Tag;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TagDeleteIntegrationTest extends TagIntegrationTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ShopRepository shopRepository;

    @Test
    @DisplayName("태그를 삭제할 수 있다.")
    public void deleteTag() {

        //given
        Tag createdTag = createTag("구룡포");
        boolean beforeDelete = createdTag.isDeleted();
        Long savedTagId = createdTag.getId();

        //when
        tagService.delete(savedTagId);
        entityManager.flush();
        entityManager.clear();
        Optional<Tag> afterDelete = tagRepository.findById(savedTagId);

        //then
        assertThat(beforeDelete).isFalse();
        assertThat(afterDelete).isEmpty();
    }

    @Test
    @DisplayName("태그를 삭제하면 ShopTag도 같이 삭제된다.")
    public void deleteTagAndShopTag() {

        //given
        Tag createdTag = createTag("창포");
        List<Tag> createdShopTags = Arrays.asList(
                createdTag,
                createTag("환여")
        );

        ShopContent content = ShopContent.builder()
                                         .name("팜스발리")
                                         .build();

        Restaurant restaurant = Restaurant.builder()
                                          .shopContent(content)
                                          .shopScore(new ShopScore())
                                          .shopImages(new ShopImages())
                                          .build();

        restaurant.addTags(createdShopTags);
        shopRepository.save(restaurant);
        entityManager.flush();
        entityManager.clear();

        //when
        tagService.delete(createdTag.getId());
        entityManager.flush();
        entityManager.clear();
        Shop shop = shopRepository.findById(restaurant.getId()).get();

        //then
        assertThat(shop.getShopTags()).extracting("tag")
                                      .extracting("name")
                                      .containsOnly("환여");
    }

    @Test
    @DisplayName("태그 하나만 가진 ShopTag는 해당 태그를 삭제할 때 예외가 발생한다.")
    public void deleteOnlyTag() {

        //given
        Tag createdTag = createTag("창포");
        List<Tag> createdShopTags = List.of(createdTag);

        ShopContent content = ShopContent.builder()
                                         .name("팜스발리")
                                         .build();

        Restaurant restaurant = Restaurant.builder()
                                          .shopContent(content)
                                          .shopScore(new ShopScore())
                                          .shopImages(new ShopImages())
                                          .build();

        restaurant.addTags(createdShopTags);
        shopRepository.save(restaurant);
        entityManager.flush();
        entityManager.clear();

        //when
        ShopTagNumberException exception = assertThrows(ShopTagNumberException.class, () -> tagService.delete(createdTag
                .getId()));

        //then
        assertThat(exception.getMessage()).isEqualTo("하나 이상의 태그가 필요합니다.");
    }
}
