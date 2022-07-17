package com.handong.rebon.integration.category;

import com.handong.rebon.category.application.dto.request.CategoryRequestDto;
import com.handong.rebon.category.application.dto.request.CategoryUpdateRequestDto;
import com.handong.rebon.exception.category.CategoryExistException;
import com.handong.rebon.exception.category.CategoryNotFoundException;
import com.handong.rebon.exception.category.CategoryParentIdNullException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CategoryUpdateIntegrationTest extends CategoryIntegrationTest {
    @Test
    @DisplayName("카테고리를 수정한다.")
    public void 카테고리_수정() {

        //given
        Long categoryId = categoryService.create("식당");
        //when
        categoryService.update(new CategoryUpdateRequestDto(categoryId, null, "카페"));
        //then
        assertThat(categoryService.findById(categoryId).getName()).isEqualTo("카페");
    }

    @Test
    @DisplayName("자식 카테고리의 이름을 수정한다.")
    public void 자식_카테고리_이름_수정() {

        //given
        Long parentId = categoryService.create("식당");
        categoryService.create(new CategoryRequestDto(parentId, "한식"));
        Long categoryId = categoryService.create(new CategoryRequestDto(parentId, "중식"));

        //when
        categoryService.update(new CategoryUpdateRequestDto(categoryId, parentId, "양식"));

        // then
        assertThat(categoryService.findById(categoryId)).extracting("name")
                                                        .isEqualTo("양식");
    }

    @Test
    @DisplayName("자식 카테고리의 이름과 부모를 수정한다.")
    public void 자식_카테고리_이름_부모_수정() {

        //given
        Long parentId = categoryService.create("식당");
        Long newParentId = categoryService.create("카페");
        categoryService.create(new CategoryRequestDto(parentId, "한식"));
        Long categoryId = categoryService.create(new CategoryRequestDto(parentId, "중식"));

        //when
        categoryService.update(new CategoryUpdateRequestDto(categoryId, newParentId, "양식"));

        // then
        assertThat(categoryService.findById(categoryId)).extracting("name")
                                                        .isEqualTo("양식");
        assertThat(categoryService.findById(categoryId)).extracting("parent")
                                                        .extracting("name")
                                                        .isEqualTo("카페");
    }

    @Test
    @DisplayName("부모카테고리를 수정할 때 이미 존재하는 카테고리 이름이면 예외 발생")
    public void 부모카테고리_수정_예외() {
        //given
        Long categoryId = categoryService.create("식당");
        categoryService.create("카페");

        //when, then
        assertThatThrownBy(() -> categoryService.update(new CategoryUpdateRequestDto(categoryId, null, "카페")))
                .isInstanceOf(CategoryExistException.class);
    }

    @Test
    @DisplayName("자식카테고리를 수정할 때 이미 존재하는 카테고리 이름이면 예외 발생")
    public void 자식카테고리_수정_예외() {
        //given
        Long parentId = categoryService.create("식당");
        categoryService.create(new CategoryRequestDto(parentId, "한식"));
        Long categoryId = categoryService.create(new CategoryRequestDto(parentId, "중식"));

        //when, then
        assertThatThrownBy(() -> categoryService.update(new CategoryUpdateRequestDto(categoryId, parentId, "한식")))
                .isInstanceOf(CategoryExistException.class);
    }

    @Test
    @DisplayName("자식카테고리를 수정할 때 존재하지 않는 부모 카테고리 id이면 예외 발생")
    public void 자식카테고리_수정_부모_존재_예외() {
        //given
        Long parentId = categoryService.create("식당");
        categoryService.create(new CategoryRequestDto(parentId, "한식"));
        Long categoryId = categoryService.create(new CategoryRequestDto(parentId, "중식"));

        //when, then
        assertThatThrownBy(() -> categoryService.update(new CategoryUpdateRequestDto(categoryId, -1L, "양식")))
                .isInstanceOf(CategoryNotFoundException.class);
    }

    @Test
    @DisplayName("자식카테고리를 수정할 때 존재하지 않는 부모 카테고리 id가 null이면 예외 발생")
    public void 자식카테고리_수정_부모_ID_예외() {
        //given
        Long parentId = categoryService.create("식당");
        categoryService.create(new CategoryRequestDto(parentId, "한식"));
        Long categoryId = categoryService.create(new CategoryRequestDto(parentId, "중식"));

        //when, then
        assertThatThrownBy(() -> categoryService.update(new CategoryUpdateRequestDto(categoryId, null, "양식")))
                .isInstanceOf(CategoryParentIdNullException.class);
    }
}
