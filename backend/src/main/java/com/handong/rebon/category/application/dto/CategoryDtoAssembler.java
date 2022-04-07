package com.handong.rebon.category.application.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.category.application.dto.response.ChildCategoryDto;
import com.handong.rebon.category.application.dto.response.RootCategoryResponseDto;
import com.handong.rebon.category.domain.Category;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryDtoAssembler {
    public static List<RootCategoryResponseDto> rootCategoryResponseDtos(List<Category> categories) {
        return categories.stream()
                         .map(category -> new RootCategoryResponseDto(
                                 category.getId(),
                                 category.getName(),
                                 childCategoryDtos(category.getChildren())))
                         .collect(Collectors.toList());
    }

    public static List<ChildCategoryDto> childCategoryDtos(List<Category> categories) {
        return categories.stream()
                         .map(ChildCategoryDto::new)
                         .collect(Collectors.toList());
    }
}
