package com.handong.rebon.integration.tag;

import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.type.Restaurant;
import com.handong.rebon.tag.domain.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.handong.rebon.shop.domain.repository.ShopRepository;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class TagDeleteIntegrationTest extends TagIntegrationTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ShopRepository shopRepository;

    @Test
    @DisplayName("태그를 삭제할 수 있다.")
    public void deleteTag() {
        //given
//        Tag createdTag = createTag("창포");
//        boolean beforeDelete = createdTag.isDeleted();
//        Long savedTagId = createdTag.getId();

        //when
//        tagService.deleteTag(savedTagId);
//        entityManager.flush();
//        entityManager.clear();
//        Optional<Tag> afterDelete = tagRepository.findById(savedTagId);

        //then
//        assertThat(beforeDelete).isFalse();
//        assertThat(afterDelete).isEmpty();
    }

    @Test
    @DisplayName("태그를 삭제하면 ShopTag도 같이 삭제된다.")
    public void deleteTagAndShopTag(){
        //given
//        Tag createdTag = createTag("창포");
//
//        List<Tag> createdShopTags = Arrays.asList(
//                createdTag,
//                createTag("환여")
//        );
//
//        ShopContent content = ShopContent.builder()
//                .name("팜스발리")
//                .build();
//
//        Restaurant restaurant = Restaurant.builder()
//                .shopContent(content)
//                .shopScore(new ShopScore())
//                .shopImages(new ShopImages())
//                .build();
//
//        restaurant.addTags(createdShopTags);
//        shopRepository.save(restaurant);

        //when
//        tagService.deleteTag(createdTag.getId());

        //then
//        assertThat(createdTag.isDeleted()).isEqualTo(true);
//        assertThat(createdTag.getShopTags()).extracting("deleted")
//                .containsOnly(true);
    }
}

