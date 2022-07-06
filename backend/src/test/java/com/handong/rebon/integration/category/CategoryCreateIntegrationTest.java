package com.handong.rebon.integration.category;

import com.handong.rebon.category.application.dto.request.CategoryRequestDto;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.exception.category.CategoryExistException;
import com.handong.rebon.exception.category.CategoryNotFoundException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CategoryCreateIntegrationTest extends CategoryIntegrationTest {
    @Test
    @DisplayName("최상단 카테고리 생성한다.")
    public void 카테고리_생성() {
        //given
        String categoryRequestName = "테스트숙소카테고리";

        //when
        Long id = categoryService.create(categoryRequestName);
        Category createdCategory = categoryRepository.getById(id);
        //then
        assertThat(createdCategory.getName()).isEqualTo(categoryRequestName);
    }

    @Test
    @DisplayName("부모있는 카테고리를 생성한다.")
    public void 카테고리_생성_부모() {
        //given
        String parentName = "테스트식당";
        String createdName = "테스트한식";
        Long parentId = categoryService.create(parentName);

        CategoryRequestDto categoryCreateRequestDto = CategoryRequestDto.builder()
                                                                        .id(parentId)
                                                                        .name(createdName)
                                                                        .build();
        //when
        Long id = categoryService.create(categoryCreateRequestDto);
        Category createdCategory = categoryRepository.getById(id);
        //then
        assertThat(createdCategory.getParent().getName()).isEqualTo(parentName);
        assertThat(createdCategory.getName()).isEqualTo(createdName);
    }

    @Test
    @DisplayName("카테고리를 부모와 함께 생성할 때 부모가 없는 부모면 예외가 발생한다.")
    public void createCategoryWithParentException() {
        //given
        long parentId = -1;
        CategoryRequestDto categoryCreateRequestDto = CategoryRequestDto.builder()
                                                                        .id(parentId)
                                                                        .name("테스트한식")
                                                                        .build();
        //when, then
        assertThatThrownBy(() -> categoryService.create(categoryCreateRequestDto))
                .isInstanceOf(CategoryNotFoundException.class);
    }

    @Test
    @DisplayName("부모 카테고리를 생성할 때 이미 존재하는 카테고리라면 예외 발생")
    public void createDuplicationParentCategory() {
        //given
        String categoryRequestName = "테스트중복한식";

        //when
        categoryService.create(categoryRequestName);
        CategoryExistException exception = assertThrows(CategoryExistException.class, () -> categoryService.create(categoryRequestName));
        //then
        assertThat(exception.getMessage()).isEqualTo("이미 존재하는 이름의 Category 입니다.");
    }

    @Test
    @DisplayName("같은 부모 카테고리를 생성할 때 이미 존재하는 카테고리라면 예외가 발생한다.")
    public void createSameParentDuplicateCategory() {

        //given
        String parentName = "테스트식당";
        String createdName = "테스트한식";
        Long parentId = categoryService.create(parentName);
        CategoryRequestDto categoryCreateRequestDto = CategoryRequestDto.builder()
                                                                        .id(parentId)
                                                                        .name(createdName)
                                                                        .build();
        categoryService.create(categoryCreateRequestDto);

        //when
        CategoryExistException exception = assertThrows(CategoryExistException.class, () -> categoryService.create(categoryCreateRequestDto));

        //then
        assertThat(exception.getMessage()).isEqualTo("이미 존재하는 이름의 Category 입니다.");
    }
}
