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
        if (tagRepository.findByName(name).isPresent()){
            throw new TagExistException();
        }
    }

    public List<Tag> findTags() {
        return tagRepository.findAll();
    }
}

