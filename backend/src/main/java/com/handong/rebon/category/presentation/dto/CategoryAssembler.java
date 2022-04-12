package com.handong.rebon.category.presentation.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.category.application.dto.request.CategoryCreateRequestDto;
import com.handong.rebon.category.application.dto.request.CategoryRequestDto;
import com.handong.rebon.category.application.dto.response.ChildCategoryDto;
import com.handong.rebon.category.application.dto.response.RootCategoryResponseDto;
import com.handong.rebon.category.presentation.dto.request.CategoryCreateRequest;
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

    public static CategoryRequestDto categoryRequestDto(CategoryRequest request){
        return new CategoryRequestDto(request.getCategoryId());
    }

    public static CategoryCreateRequestDto categoryCreateRequestDto(CategoryCreateRequest request){
        return new CategoryCreateRequestDto(request.getParentId(), request.getName());
    }
}
