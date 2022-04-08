package com.handong.rebon.tag.application;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.exception.tag.NoSuchTagException;
import com.handong.rebon.exception.tag.TagExistException;
import com.handong.rebon.tag.application.dto.TagResponseDto;
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

    @Transactional(readOnly = true)
    public Tag findById(Long id) {
        return tagRepository.findById(id)
                            .orElseThrow(NoSuchTagException::new);
    }

    @Transactional(readOnly = true)
    public List<Tag> findAllContainIds(List<Long> tags) {
        return tags.stream()
                   .map(this::findById)
                   .collect(Collectors.toList());
    }

    // temp 태그 조회 기능 머지되면 변경할 메서드
    @Transactional(readOnly = true)
    public List<TagResponseDto> findAll() {
        return tagRepository.findAll().stream()
                            .map(TagResponseDto::of)
                            .collect(Collectors.toList());
    }
}

