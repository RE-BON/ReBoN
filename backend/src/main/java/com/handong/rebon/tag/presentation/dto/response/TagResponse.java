package com.handong.rebon.tag.presentation.dto.response;

import com.handong.rebon.tag.application.dto.response.TagResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TagResponse {
    private Long id;
    private String name;
    private int count;

    public static TagResponse from(TagResponseDto tagResponseDto) {
        return new TagResponse(tagResponseDto.getId(), tagResponseDto.getName(), tagResponseDto.getCount());
    }
}
