package com.handong.rebon.category.presentation;

import java.util.List;

import com.handong.rebon.category.application.CategoryService;
import com.handong.rebon.category.application.dto.response.RootCategoryResponseDto;
import com.handong.rebon.category.presentation.dto.CategoryAssembler;
import com.handong.rebon.category.presentation.dto.response.RootCategoryResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ApiCategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<RootCategoryResponse>> getRootCategories() {
        List<RootCategoryResponseDto> rootCategorieDtos = categoryService.findRootCategoriesAndChildren();
        List<RootCategoryResponse> responses = CategoryAssembler.rootCategoryResponses(rootCategorieDtos);
        return ResponseEntity.ok(responses);
    }
}
