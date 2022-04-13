package com.handong.rebon.common.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.handong.rebon.tag.domain.Tag;

import com.handong.rebon.tag.domain.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@Component
@ActiveProfiles("test")
public class AdminTagRegister {

    @Autowired
    private TagRepository tagRepository;

    public Map<String, Tag> register(String... names) {
        List<Tag> tags = new ArrayList<>();
        for (String name : names) {
            tags.add(new Tag(name));
        }
        tagRepository.saveAll(tags);
        return tags.stream()
                .collect(Collectors.toMap(Tag::getName, Function.identity()));
    }
}
