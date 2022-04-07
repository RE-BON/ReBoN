package com.handong.rebon.integration.category;

import javax.transaction.Transactional;

import com.handong.rebon.category.application.CategoryService;
import com.handong.rebon.category.application.dto.request.CategoryRequestDto;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.category.domain.repository.CategoryRepository;
import com.handong.rebon.integration.IntegrationTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class CategoryIntegrationTest extends IntegrationTest {
    @Autowired
    protected CategoryService categoryService;

    @Autowired
    protected CategoryRepository categoryRepository;

    public Category createCategory(String categoryName){
        Long id = categoryService.create(categoryName);
        return categoryRepository.getById(id);
    }

    public Category createCategoryWithParent(Long parentId, String categoryName){
        CategoryRequestDto categoryRequestDto = CategoryRequestDto.builder()
                                                                  .parentId(parentId)
                                                                  .name(categoryName)
                                                                  .build();
        Long id = categoryService.create(categoryRequestDto);
        return categoryRepository.getById(id);
    }
}
