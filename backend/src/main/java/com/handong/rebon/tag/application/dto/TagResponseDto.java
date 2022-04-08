package com.handong.rebon.tag.application.dto;

import com.handong.rebon.tag.domain.Tag;

import lombok.Getter;

@Getter
public class TagResponseDto {
    private Long id;
    private String name;

    public TagResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static TagResponseDto of(Tag tag) {
        return new TagResponseDto(tag.getId(), tag.getName());
    }
}
