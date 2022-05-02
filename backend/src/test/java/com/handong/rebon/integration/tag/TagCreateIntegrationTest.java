package com.handong.rebon.integration.tag;

import com.handong.rebon.exception.tag.TagExistException;
import com.handong.rebon.tag.application.TagService;
import com.handong.rebon.tag.domain.Tag;
import com.handong.rebon.tag.domain.repository.TagRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
//@WebAppConfiguration
//@ContextConfiguration()
public class TagCreateIntegrationTest extends TagIntegrationTest {

    @Autowired
    TagService tagService;

    @Autowired
    TagRepository tagRepository;

    @Test
    @DisplayName("태그를 생성한다.")
    void createTag() {
        // given
        String tagName = "남구";

        // when
        Long id = tagService.createTag(tagName);
        Tag newTag = tagRepository.getById(id);

        // then
        assertThat(newTag).extracting("name").isEqualTo(tagName);
    }

    @Test
    @DisplayName("태그 이름이 중복되면 예외가 발생한다.")
    void createExistTagWithException() {
        // given
        String tagName = "장성동";

        // when
        tagService.createTag(tagName);
        TagExistException exception = assertThrows(TagExistException.class, () -> tagService.createTag(tagName));

        //then
        assertThat(exception.getMessage()).isEqualTo("이미 존재하는 태그 입니다.");
    }
}
