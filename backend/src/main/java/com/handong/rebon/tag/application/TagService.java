package com.handong.rebon.tag.application;

import java.util.List;

import com.handong.rebon.exception.tag.TagExistException;
import com.handong.rebon.tag.domain.Tag;
import com.handong.rebon.tag.domain.repository.TagRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;

    @Transactional
    public Long createTag(String tagName) {
        validateDuplicateTag(tagName);

        Tag newTag = new Tag(tagName);

        Tag savedTag = tagRepository.save(newTag);
        return savedTag.getId();
    }

    private void validateDuplicateTag(String name) {
        if (tagRepository.existsByName(name)) {
            throw new TagExistException();
        }
    }

    public List<Tag> findAll(List<Long> tags) {
        return tagRepository.findAll();
    }

    public List<Tag> findTags() {
        return tagRepository.findAll();
    }


    public Tag findById(Long id) {
        return tagRepository.findById(id)
                            .orElseThrow(IllegalArgumentException::new);
    }
}

