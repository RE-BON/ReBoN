package com.handong.rebon.category.presentation;

import java.util.List;

import com.handong.rebon.category.application.CategoryService;
import com.handong.rebon.category.application.dto.request.CategoryCreateRequestDto;
import com.handong.rebon.category.application.dto.request.CategoryRequestDto;
import com.handong.rebon.category.application.dto.response.RootCategoryResponseDto;
import com.handong.rebon.category.presentation.dto.CategoryAssembler;
import com.handong.rebon.category.presentation.dto.request.CategoryCreateRequest;
import com.handong.rebon.category.presentation.dto.request.CategoryRequest;
import com.handong.rebon.category.presentation.dto.response.RootCategoryResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminCategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories/new")
    public String categoryCreateForm(Model model) {

        List<RootCategoryResponseDto> rootCategorieDtos = categoryService.findRootCategoriesAndChildren();
        List<RootCategoryResponse> responses = CategoryAssembler.rootCategoryResponses(rootCategorieDtos);
        model.addAttribute("categories", responses);

        return "category/createForm";
    }

    @GetMapping("/categories/remove")
    public String categoryDeleteForm(Model model) {

        List<RootCategoryResponseDto> rootCategorieDtos = categoryService.findRootCategoriesAndChildren();
        List<RootCategoryResponse> responses = CategoryAssembler.rootCategoryResponses(rootCategorieDtos);
        model.addAttribute("categories", responses);

        return "category/deleteForm";
    }

    @PostMapping("/categories")
    public String createCategory(CategoryCreateRequest categoryRequest) {

        CategoryCreateRequestDto categoryCreateRequestDto = CategoryAssembler.categoryCreateRequestDto(categoryRequest);
        categoryService.create(categoryCreateRequestDto);

        return "home";
    }

    @DeleteMapping("/categories")
    public String deleteCategory(CategoryRequest request) {

        CategoryRequestDto categoryRequestDto = CategoryAssembler.categoryRequestDto(request);
        categoryService.delete(categoryRequestDto);

        return "home";
    }
}
