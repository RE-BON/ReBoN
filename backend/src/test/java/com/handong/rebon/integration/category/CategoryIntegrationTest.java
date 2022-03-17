package com.handong.rebon.integration.category;

import com.handong.rebon.exception.category.CategoryNoParentException;
import com.handong.rebon.shop.application.CategoryService;
import com.handong.rebon.shop.application.dto.CategoryRequestDto;
import com.handong.rebon.shop.domain.category.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class CategoryIntegrationTest {
    @Autowired
    private CategoryService categoryService;

    @Test
    @DisplayName("최상단 카테고리 생성")
    public void createCategory() {
        //given
        String categoryRequestName = "테스트숙소카테고리";

        //when
        Long id = categoryService.create(categoryRequestName);
        //then
        Assertions.assertThat(id).isNotNull();
    }

    @Test
    @DisplayName("부모있는 카테고리 생성")
    public void createCategoryWithParent() {
        //given
        categoryService.create("테스트식당");
        CategoryRequestDto categoryRequestDto = CategoryRequestDto.builder()
                .name("테스트한식")
                .parent("테스트식당")
                .build();
//        //when
        Long id = categoryService.create(categoryRequestDto);
        System.out.println(id);
//        //then
        Assertions.assertThat(id).isNotNull();
    }
    @Test
    @DisplayName("카테고리를 부모와 함께 생성할 때 부모가 없는 부모면 예외 발생")
    public void createCategoryWithParentException() {
        //given
        CategoryRequestDto categoryRequestDto = CategoryRequestDto.builder()
                .name("테스트한식")
                .parent("존재하지않는카테고리")
                .build();
//        //when
//        //then
        Assertions.assertThatThrownBy(() -> categoryService.create(categoryRequestDto))
                        .isInstanceOf(CategoryNoParentException.class);
    }
}
