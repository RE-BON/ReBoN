package com.handong.rebon.category.presentation.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.category.application.dto.request.CategoryRequestDto;
import com.handong.rebon.category.application.dto.request.CategoryUpdateRequestDto;
import com.handong.rebon.category.application.dto.response.ChildCategoryDto;
import com.handong.rebon.category.application.dto.response.RootCategoryResponseDto;
import com.handong.rebon.category.presentation.dto.request.CategoryRequest;
import com.handong.rebon.category.presentation.dto.response.ChildCategoryResponse;
import com.handong.rebon.category.presentation.dto.response.RootCategoryResponse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryAssembler {
    public static List<RootCategoryResponse> rootCategoryResponses(List<RootCategoryResponseDto> rootCategories) {
        return rootCategories.stream()
                             .map(rootCategory -> new RootCategoryResponse(
                                     rootCategory.getId(),
                                     rootCategory.getName(),
                                     childCategoryResponses(rootCategory.getChildren())))
                             .collect(Collectors.toList());
    }

    public static List<ChildCategoryResponse> childCategoryResponses(List<ChildCategoryDto> childCategories) {
        return childCategories.stream()
                              .map(ChildCategoryResponse::new)
                              .collect(Collectors.toList());
    }

    public static CategoryUpdateRequestDto categoryUpdateRequestDto(Long id, CategoryRequest request) {
        return new CategoryUpdateRequestDto(id, request.getParentId(), request.getName());
    }

    public static CategoryRequestDto categoryRequestDto(CategoryRequest categoryRequest) {
        return new CategoryRequestDto(categoryRequest.getParentId(), categoryRequest.getName());
    }
}
