package com.handong.rebon.shop.domain.tag.application;

import com.handong.rebon.exception.tag.TagExistException;
import com.handong.rebon.shop.domain.tag.application.dto.TagRequestDto;
import com.handong.rebon.shop.domain.tag.domain.Tag;
import com.handong.rebon.shop.domain.tag.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    @Autowired
    TagRepository tagRepository;

    public Long createTag(String name) {
        validateDuplicateTag(name);

        Tag newTag = Tag.builder()
                .name(name)
                .build();

        tagRepository.save(newTag);
        return newTag.getId();
    }

    public Long createTag(TagRequestDto tagRequestDto) {
        String tagName = tagRequestDto.getName();
        validateDuplicateTag(tagName);

        Tag newTag = Tag.builder()
                .name(tagName)
                .build();

        tagRepository.save(newTag);
        return newTag.getId();
    }

    private void validateDuplicateTag(String name) {
        Optional<Tag> tag = tagRepository.findByName(name);
        if (tag.isPresent())
            throw new TagExistException();
    }

    public List<Tag> findTags() {
        return tagRepository.findAll();
    }
}

