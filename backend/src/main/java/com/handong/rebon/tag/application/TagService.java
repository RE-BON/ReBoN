package com.handong.rebon.tag.application;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.tag.domain.Tag;
import com.handong.rebon.tag.domain.repository.TagRepository;

import org.springframework.stereotype.Service;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    // 태그 담당이 아니라 Shop 메서드가 잘 돌아가는지만 확인하기 위해 임시로 만든것.
    // 태그 파트랑 머지할 때 삭제하면 될듯
    public List<Tag> findAll(List<Long> tags) {
        return tags.stream()
                   .map(t -> tagRepository.findById(t).get())
                   .collect(Collectors.toList());
    }
}
