package com.handong.rebon.category.presentation;

import java.util.List;

import com.handong.rebon.category.application.CategoryService;
import com.handong.rebon.category.application.dto.request.CategoryRequestDto;
import com.handong.rebon.category.application.dto.request.CategoryUpdateRequestDto;
import com.handong.rebon.category.application.dto.response.RootCategoryResponseDto;
import com.handong.rebon.category.presentation.dto.CategoryAssembler;
import com.handong.rebon.category.presentation.dto.request.CategoryRequest;
import com.handong.rebon.category.presentation.dto.response.RootCategoryResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String createCategory(CategoryRequest createRequest) {

        CategoryRequestDto categoryRequestDto = CategoryAssembler.categoryRequestDto(createRequest);
        categoryService.create(categoryRequestDto);

        return "home";
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return "home";
    }

    @PutMapping("/categories/{id}")
    public String updateCategory(@PathVariable Long id, CategoryRequest categoryRequest) {
        CategoryUpdateRequestDto categoryUpdateRequestDto = CategoryAssembler.categoryUpdateRequestDto(id, categoryRequest);

        categoryService.update(categoryUpdateRequestDto);

        return "home";
    }

}
