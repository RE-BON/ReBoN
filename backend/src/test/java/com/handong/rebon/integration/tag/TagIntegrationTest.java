package com.handong.rebon.integration.tag;

import javax.transaction.Transactional;

import com.handong.rebon.integration.IntegrationTest;
import com.handong.rebon.tag.application.TagService;
import com.handong.rebon.tag.domain.Tag;
import com.handong.rebon.tag.domain.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Transactional
public class TagIntegrationTest extends IntegrationTest {

    @Autowired
    protected TagService tagService;

    @Autowired
    protected TagRepository tagRepository;

    public Tag createTag(String tagName) {
        Long id = tagService.createTag(tagName);
        return tagRepository.getById(id);
    }
}
