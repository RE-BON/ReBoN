package com.handong.rebon.integration.tag;

import com.handong.rebon.tag.domain.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class TagDeleteIntegrationTest extends TagIntegrationTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("태그를 삭제할 수 있다.")
    public void deleteTag() {
        //given
        Tag createdTag = createTag("창포");
        boolean beforeDelete = createdTag.isDeleted();
        Long savedTagId = createdTag.getId();

        //when
        tagService.deleteTag(savedTagId);
        entityManager.flush();
        entityManager.clear();
        Optional<Tag> afterDelete = tagRepository.findById(savedTagId);

        //then
        assertThat(beforeDelete).isFalse();
        assertThat(afterDelete).isEmpty();
    }
}

