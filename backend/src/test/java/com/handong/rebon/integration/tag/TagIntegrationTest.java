package com.handong.rebon.integration.tag;

import com.handong.rebon.shop.domain.tag.domain.Tag;
import com.handong.rebon.shop.domain.tag.domain.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import static org.junit.jupiter.api.Assertions.assertEquals;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class TagIntegrationTest {

    @Autowired
    TagRepository tagRepository;

    @Test
    @Rollback(value = false)
    void createTag() {
        // given
        String tagName = "북구";
        Tag newTag = Tag.builder()
                .name(tagName)
                .build();

        // when
        Tag savedTag = tagRepository.save(newTag);

        // then
        assertEquals(tagName, savedTag.getName());
    }

    @Test
    @Rollback(value = false)
    void createNotValidTag() {
        // given
        String tagName = "";
        Tag newTag = Tag.builder()
                .name(tagName)
                .build();

        // when
        Tag savedTag = tagRepository.save(newTag);

        // then
        assertEquals(tagName, savedTag.getName());
    }
}
