package com.handong.rebon.category.presentation;

import java.util.List;

import com.handong.rebon.category.application.CategoryService;
import com.handong.rebon.category.application.dto.request.CategoryCreateRequestDto;
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
    // ToDo PathVariable 로 바꾼 후 관리자 페이지는 update 페이지 만들 때 일괄적으로 구현할 예정
    @PostMapping("/categories/{id}")
    public String createCategory(@PathVariable Long id, CategoryRequest categoryRequest) {

        CategoryCreateRequestDto categoryCreateRequestDto = CategoryAssembler.categoryCreateRequestDto(id ,categoryRequest);
        categoryService.create(categoryCreateRequestDto);

        return "home";
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return "home";
    }

}
