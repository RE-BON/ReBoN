package com.handong.rebon.category.application.dto.response;

import com.handong.rebon.category.domain.Category;

import lombok.Getter;

@Getter
public class ChildCategoryDto {
    private Long id;
    private String name;

    public ChildCategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
