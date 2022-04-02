package com.handong.rebon.integration.tag;

import com.handong.rebon.exception.tag.TagExistException;
import com.handong.rebon.exception.tag.TagIdException;
import com.handong.rebon.tag.application.TagService;
import com.handong.rebon.tag.domain.Tag;
import com.handong.rebon.tag.domain.repository.TagRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
public class TagReadIntegrationTest extends TagIntegrationTest{
    @Test
    @DisplayName("id에 따라 태그를 조회할 수 있다.")
    public void findTagById(){
        //given
        createTag("북구");
        Tag createdTag= createTag("양덕");
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
                .isInstanceOf(TagIdException.class);
    }
}
