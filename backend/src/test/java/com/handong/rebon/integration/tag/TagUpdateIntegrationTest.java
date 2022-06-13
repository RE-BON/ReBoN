package com.handong.rebon.integration.tag;

import com.handong.rebon.exception.tag.TagExistException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TagUpdateIntegrationTest extends TagIntegrationTest {

    @Test
    @DisplayName("태그를 수정할 수 있다.")
    public void updateTag() {

        //given
        Long tagId = tagService.createTag("구룡포");

        //when
        tagService.update(tagId, "대보");

        //then
        assertThat(tagService.findById(tagId).getName()).isEqualTo("대보");
    }

    @Test
    @DisplayName("태그를 수정할 때 이미 존재하는 태그의 이름이면 수정하면 예외가 발생한다.")
    public void updateExistTagNameWithException() {

        //given
        Long tagId = tagService.createTag("구룡포");
        tagService.createTag("오천");

        //when
        TagExistException exception = assertThrows(TagExistException.class, () -> tagService.update(tagId, "오천"));

        //then
        assertThat(exception.getMessage()).isEqualTo("이미 존재하는 태그 입니다.");
    }
}