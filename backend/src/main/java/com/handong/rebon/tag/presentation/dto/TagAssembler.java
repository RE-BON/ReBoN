package com.handong.rebon.tag.presentation.dto;

import com.handong.rebon.tag.application.dto.response.TagResponseDto;
import com.handong.rebon.tag.presentation.dto.response.TagResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TagAssembler {

    public static List<TagResponse> tagResponses(List<TagResponseDto> tags) {
        return tags.stream()
                .map(tag -> new TagResponse(tag.getId(), tag.getName()))
                .collect(Collectors.toList());
    }
}
