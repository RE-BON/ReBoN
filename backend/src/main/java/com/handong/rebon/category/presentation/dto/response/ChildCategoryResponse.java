package com.handong.rebon.category.presentation.dto.response;

import com.handong.rebon.category.application.dto.response.ChildCategoryDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChildCategoryResponse {
    private Long id;
    private String name;

    public ChildCategoryResponse(ChildCategoryDto childCategory) {
        this.id = childCategory.getId();
        this.name = childCategory.getName();
    }
}
