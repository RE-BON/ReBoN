package com.handong.rebon.tag.presentation.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.tag.application.dto.response.TagResponseDto;
import com.handong.rebon.tag.presentation.dto.response.TagResponse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TagAssembler {

    public static List<TagResponse> tagResponses(List<TagResponseDto> tags) {
        return tags.stream()
                   .map(tag -> new TagResponse(tag.getId(), tag.getName(), tag.getCount()))
                   .collect(Collectors.toList());
    }
}
