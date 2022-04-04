package com.handong.rebon.category.application.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.category.domain.Category;

import lombok.Getter;

@Getter
public class RootCategoryResponse {
    private Long id;
    private String name;
    private List<ChildCategoryDto> children;

    public RootCategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.children = category.getChildren()
                                .stream()
                                .map(ChildCategoryDto::new)
                                .collect(Collectors.toList());
    }
}
