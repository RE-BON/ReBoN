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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
public class TagReadIntegrationTest extends TagIntegrationTest{
    @Test
    @DisplayName("id에 따라 태그를 조회할 수 있다.")
    public void findTagById(){
        //given
        Tag createdTag= createTag("환호");
        //when
        Tag tagId = tagService.findById(createdTag.getId());
        //then
        assertThat(tagId).isEqualTo(createdTag);
    }

    @Test
    @DisplayName("모든 태그를 조회할 수 있다.")
    public void findAllTags(){
        //given
        String tagName = "장량";

        List<Tag> allTags = tagService.findTags();

        Tag tags = allTags.get(0);
        assertThat(tags.getName()).isEqualTo(tagName);
        //when
        //then
    }

}
