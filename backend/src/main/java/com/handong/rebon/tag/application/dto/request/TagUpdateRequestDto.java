package com.handong.rebon.tag.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TagUpdateRequestDto {
    private Long id;
    private String name;
}
