package com.handong.rebon.category.presentation;

import java.util.List;

import com.handong.rebon.category.application.CategoryService;
import com.handong.rebon.category.application.dto.request.CategoryRequestDto;
import com.handong.rebon.category.application.dto.response.RootCategoryResponseDto;
import com.handong.rebon.category.presentation.dto.CategoryAssembler;
import com.handong.rebon.category.presentation.dto.request.CategoryRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminCategoryController {

    private final CategoryService categoryService;

    @DeleteMapping("/category")
    public String deleteCategory(@RequestBody CategoryRequest request) {

        CategoryRequestDto categoryRequestDto = CategoryAssembler.categoryRequestDto(request);
        categoryService.delete(categoryRequestDto);

        List<RootCategoryResponseDto> rootCategories = categoryService.findRootCategoriesAndChildren();

        // ToDo Admin 페이지 구현 예정
        return "";
    }
}
