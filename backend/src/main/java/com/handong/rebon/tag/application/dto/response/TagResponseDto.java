package com.handong.rebon.tag.application.dto.response;

import com.handong.rebon.tag.domain.Tag;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TagResponseDto {
    private Long id;
    private String name;

    public static TagResponseDto from(Tag tag) {
        return new TagResponseDto(tag.getId(), tag.getName());
    }
}
