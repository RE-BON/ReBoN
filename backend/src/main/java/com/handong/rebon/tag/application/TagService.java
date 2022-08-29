package com.handong.rebon.tag.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.handong.rebon.exception.tag.NoSuchTagException;
import com.handong.rebon.exception.tag.TagExistException;
import com.handong.rebon.tag.application.dto.TagDtoAssembler;
import com.handong.rebon.tag.application.dto.request.TagUpdateRequestDto;
import com.handong.rebon.tag.application.dto.response.TagResponseDto;
import com.handong.rebon.tag.domain.Tag;
import com.handong.rebon.tag.domain.repository.TagRepository;
import com.handong.rebon.tag.domain.repository.TagSearchRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TagService {
    private final TagRepository tagRepository;
    private final TagSearchRepository tagSearchRepository;

    @Transactional
    public Long createTag(String tagName) {
        validateDuplicateTag(tagName);

        Tag newTag = new Tag(tagName);

        Tag savedTag = tagRepository.save(newTag);
        tagSearchRepository.save(newTag);
        return savedTag.getId();
    }

    private void validateDuplicateTag(String name) {
        if (tagRepository.existsByName(name)) {
            throw new TagExistException();
        }
    }

    @Transactional(readOnly = true)
    public List<TagResponseDto> findTags() {
        return TagDtoAssembler.tagResponseDtos(tagRepository.findAll());
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

    @Transactional
    public void delete(Long id) {
        Tag tag = findById(id);
        tag.delete();
    }

    @Transactional
    public void update(TagUpdateRequestDto tagUpdateRequestDto) {
        Tag tag = findById(tagUpdateRequestDto.getId());

        if (!(tag.isSameName(tagUpdateRequestDto.getName()))) {
            validateDuplicateTag(tagUpdateRequestDto.getName());
        }

        tag.update(tagUpdateRequestDto.getName());
    }

    @Transactional(readOnly = true)
    public List<TagResponseDto> searchByKeyword(String keyword, Pageable pageable) {
        return tagSearchRepository.searchByKeyword(keyword.strip(), pageable)
                                  .stream()
                                  .map(TagResponseDto::from)
                                  .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TagResponseDto> findTopTags() {
        List<Tag> tags = tagRepository.findTop8ByOrderByCountDesc();
        return tags.stream()
                   .map(TagResponseDto::from)
                   .collect(Collectors.toList());
    }

    @Transactional
    public List<Tag> getTags(String address) {
        List<Tag> tags = new ArrayList<>();

        String[] addressUnits = address.split(" ");
        for (String name : addressUnits) {
            Optional<Tag> tag = tagRepository.findByName(name);

            if (tag.isEmpty()) {
                Tag newTag = tagRepository.save(new Tag(name));
                tagSearchRepository.save(newTag);
                tags.add(newTag);
                continue;
            }

            tags.add(tag.get());
        }

        return tags;
    }
}
