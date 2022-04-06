package com.handong.rebon.category.presentation.dto.response;

import com.handong.rebon.category.application.dto.response.ChildCategoryDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChildCategoryResponse {

    private Long id;
    private String name;

    public ChildCategoryResponse(ChildCategoryDto childCategory) {
        id = childCategory.getId();
        name = childCategory.getName();
    }
}
