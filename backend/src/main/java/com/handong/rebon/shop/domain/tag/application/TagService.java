package com.handong.rebon.shop.domain.tag.application;

import com.handong.rebon.exception.tag.TagExistException;
import com.handong.rebon.shop.domain.tag.application.dto.TagRequestDto;
import com.handong.rebon.shop.domain.tag.domain.Tag;
import com.handong.rebon.shop.domain.tag.domain.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    public Long createTag(TagRequestDto tagRequestDto) {
        String tagName = tagRequestDto.getName();
        validateDuplicateTag(tagName);

        Tag newTag = Tag.builder()
                .name(tagName)
                .build();

        Tag savedTag = tagRepository.save(newTag);
        return savedTag.getId();
    }

    private void validateDuplicateTag(String name) {
        tagRepository.findByName(name)
                .orElseThrow(TagExistException::new);
    }
}

