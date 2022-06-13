package com.handong.rebon.tag.application.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.tag.domain.Tag;
import com.handong.rebon.tag.application.dto.response.TagResponseDto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TagDtoAssembler {
    public static List<TagResponseDto> tagResponseDtos(List<Tag> tags) {
        return tags.stream()
                   .map(tag -> new TagResponseDto(tag.getId(), tag.getName(), tag.getCount()))
                   .collect(Collectors.toList());
    }
}
