package com.handong.rebon.integration.category;

import com.handong.rebon.category.application.CategoryService;
import com.handong.rebon.category.application.dto.request.CategoryRequestDto;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.category.domain.repository.CategoryRepository;
import com.handong.rebon.integration.IntegrationTest;

import org.springframework.beans.factory.annotation.Autowired;

public class CategoryIntegrationTest extends IntegrationTest {

    @Autowired
    protected CategoryService categoryService;

    @Autowired
    protected CategoryRepository categoryRepository;

    public Category createCategory(String categoryName) {
        Long id = categoryService.create(categoryName);
        return categoryRepository.getById(id);
    }

    public Category createCategoryWithParent(Long parentId, String categoryName) {
        CategoryRequestDto categoryCreateRequestDto = CategoryRequestDto.builder()
                                                                        .id(parentId)
                                                                        .name(categoryName)
                                                                        .build();
        Long id = categoryService.create(categoryCreateRequestDto);
        return categoryRepository.getById(id);
    }
}
