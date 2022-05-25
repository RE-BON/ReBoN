package com.handong.rebon.integration.category;

import com.handong.rebon.category.application.dto.request.CategoryRequestDto;
import com.handong.rebon.exception.category.CategoryExistException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CategoryUpdateIntegrationTest extends CategoryIntegrationTest{
    @Test
    @DisplayName("카테고리를 수정한다.")
    public void 카테고리_수정(){
        //given
        Long categoryId = categoryService.create("식당");
        //when
        categoryService.update(new CategoryRequestDto(categoryId, "카페"));
        //then
        assertThat(categoryService.findById(categoryId).getName()).isEqualTo("카페");
    }

    @Test
    @DisplayName("부모카테고리를 수정할 때 이미 존재하는 카테고리 이름이면 예외 발생")
    public void 부모카테고리_수정_예외(){
        //given
        categoryService.create("식당");
        String categoryName = "카페";
        Long categoryId = categoryService.create(categoryName);

        //when, then
        assertThatThrownBy(() -> categoryService.update(new CategoryRequestDto(categoryId, "식당")))
                .isInstanceOf(CategoryExistException.class);
    }

    @Test
    @DisplayName("자식카테고리를 수정할 때 이미 존재하는 카테고리 이름이면 예외 발생")
    public void 자식카테고리_수정_예외(){
        //given
        Long parentId = categoryService.create("식당");
        categoryService.create(new CategoryRequestDto(parentId, "한식"));
        Long categoryId = categoryService.create(new CategoryRequestDto(parentId, "중식"));
        //when, then
        assertThatThrownBy(() -> categoryService.update(new CategoryRequestDto(categoryId, "한식")))
                .isInstanceOf(CategoryExistException.class);
    }
}
