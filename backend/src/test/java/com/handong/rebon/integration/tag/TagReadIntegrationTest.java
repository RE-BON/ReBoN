package com.handong.rebon.integration.tag;

import java.util.List;

import com.handong.rebon.exception.tag.NoSuchTagException;
import com.handong.rebon.tag.application.dto.response.TagResponseDto;
import com.handong.rebon.tag.domain.Tag;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

public class TagReadIntegrationTest extends TagIntegrationTest {
    @Test
    @DisplayName("id에 따라 태그를 조회할 수 있다.")
    public void findTagById() {
        //given
        Tag createdTag = createTag("환호");
        //when
        Tag tagId = tagService.findById(createdTag.getId());
        //then
        assertThat(tagId).isEqualTo(createdTag);
    }

    @Test
    @DisplayName("존재하지 않는 id의 태그를 요청하면 예외가 발생한다.")
    public void findTagByWrongId() {
        //given
        Long requestTagId = -1L;
        //when, then
        assertThatThrownBy(() -> tagService.findById(requestTagId))
                .isInstanceOf(NoSuchTagException.class);
    }

    @Test
    @DisplayName("모든 태그를 조회할 수 있다.")
    public void findAllTags() {
        //given
        Long tagId1 = tagService.createTag("장량");
        Long tagId2 = tagService.createTag("환호");
        Long tagId3 = tagService.createTag("환여");

        //when
        List<TagResponseDto> allTags = tagService.findTags();

        //then
        assertThat(allTags).hasSize(3)
                .extracting("name")
                .contains("장량", "환호", "환여");
    }
}