package com.handong.rebon.category.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateRequestDto {
    private Long id;
    private Long parentId;
    private String name;
}
