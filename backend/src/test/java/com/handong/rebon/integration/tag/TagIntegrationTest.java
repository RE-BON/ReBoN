package com.handong.rebon.integration.tag;

import com.handong.rebon.shop.domain.tag.application.TagService;
import com.handong.rebon.shop.domain.tag.application.dto.TagRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class TagIntegrationTest {

    @Autowired
    TagService tagService;

    @Test
    @DisplayName("태그 생성")
    void createTag() {
        // given
        String tagName = "흥해";
        TagRequestDto tagRequestDto = new TagRequestDto(tagName);

        // when
        Long id = tagService.createTag(tagRequestDto);

        // then
        assertThat(id).isNotNull();
    }

    @Test
    @DisplayName("유효하지 않은 태그 생성")
    void createNotValidTag() {
        // given
        String tagName = "";
        TagRequestDto tagRequestDto = new TagRequestDto(tagName);

        // when
        Long id = tagService.createTag(tagRequestDto);

        // then
        assertThat(id).isNotNull();
    }
}
