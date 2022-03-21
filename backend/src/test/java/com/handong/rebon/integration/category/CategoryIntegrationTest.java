package com.handong.rebon.integration.category;

import com.handong.rebon.shop.application.CategoryService;
import com.handong.rebon.shop.application.dto.CategoryRequestDto;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.repository.CategoryRepository;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class CategoryIntegrationTest {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("최상단 카테고리 생성")
    public void createCategory() {
        //given
        String categoryRequestName = "테스트숙소카테고리";

        //when
        Long id = categoryService.create(categoryRequestName);
        Category createdCategory = categoryRepository.getById(id);
        //then
        assertThat(createdCategory.getName()).isEqualTo(categoryRequestName);
    }

    @Test
    @DisplayName("부모있는 카테고리 생성")
    public void createCategoryWithParent() {
        //given
        String parentName = "테스트식당";
        String createdName = "테스트한식";
        Long parentId = categoryService.create(parentName);

        CategoryRequestDto categoryRequestDto = CategoryRequestDto.builder()
                                                                  .parentId(parentId)
                                                                  .name(createdName)
                                                                  .build();
        //when
        Long id = categoryService.create(categoryRequestDto);
        Category createdCategory = categoryRepository.getById(id);
        //then
        assertThat(createdCategory.getParent().getName()).isEqualTo(parentName);
        assertThat(createdCategory.getName()).isEqualTo(createdName);
    }

    @Test
    @DisplayName("카테고리를 부모와 함께 생성할 때 부모가 없는 부모면 예외 발생")
    public void createCategoryWithParentException() {
        //given
        long parentId = -1;
        CategoryRequestDto categoryRequestDto = CategoryRequestDto.builder()
                                                                  .parentId(parentId)
                                                                  .name("테스트한식")
                                                                  .build();
        //when
        final RuntimeException exception = assertThrows(RuntimeException.class, () -> categoryService.create(categoryRequestDto));
        //then
        assertThat(exception.getMessage()).isEqualTo("저장하려는 카테고리의 부모 카테고리가 존재하지 않습니다.");

    }
}
