package com.handong.rebon.integration.category;

import java.util.List;
import javax.persistence.EntityManager;

import com.handong.rebon.category.application.dto.request.CategoryRequestDto;
import com.handong.rebon.category.application.dto.response.RootCategoryResponseDto;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.exception.category.CategoryAlreadyDeletedException;
import com.handong.rebon.exception.category.CategoryNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CategoryDeleteIntegrationTest extends CategoryIntegrationTest {
    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("자식 카테고리를 삭제한다.")
    public void 자식_카테고리_삭제() {

        //given
        String parentName = "식당";
        String childName = "한식";
        String childName2 = "중식";
        Category parent = createCategory(parentName);
        Category child = createCategoryWithParent(parent.getId(), childName);
        createCategoryWithParent(parent.getId(), childName2);
        categoryService.delete(new CategoryRequestDto(child.getId()));
        entityManager.flush();
        entityManager.clear();
        //then
        assertThat(categoryService.findById(parent.getId()).getChildren())
                .extracting("name")
                .containsOnly("중식");
        assertThat(categoryService.findById(parent.getId()).getChildren())
                .extracting("name")
                .doesNotContain("한식");
    }

    @Test
    @DisplayName("루트 카테고리를 삭제하면 자식 카테고리들까지 모두 삭제된다.")
    public void 루트_카테고리_삭제() {

        //given
        String parentName = "식당";
        Category parent = createCategory(parentName);
        Category child = createCategoryWithParent(parent.getId(), "한식");
        createCategoryWithParent(parent.getId(), "중식");
        createCategoryWithParent(parent.getId(), "양식");

        //when
        categoryService.delete(new CategoryRequestDto(parent.getId()));
        entityManager.flush();
        entityManager.clear();
        //then
        assertThatThrownBy(() -> categoryService.findById(parent.getId()))
                .isInstanceOf(CategoryNotFoundException.class);
        assertThatThrownBy(() -> categoryService.findById(child.getId()))
                .isInstanceOf(CategoryNotFoundException.class);
    }

    @Test
    @DisplayName("이미 삭제된 카테고리를 삭제요청 할 경우 예외 발생")
    public void 삭제된_카테고리_삭제() {

        //given
        String parentName = "식당";
        Category parent = createCategory(parentName);
        Category child = createCategoryWithParent(parent.getId(), "한식");
        CategoryRequestDto requestDto = new CategoryRequestDto(child.getId());
        categoryService.delete(requestDto);
        entityManager.flush();
        entityManager.clear();
        //        categoryRepository.flush();

        //when, then
        assertThatThrownBy(() -> categoryService.delete(requestDto))
                .isInstanceOf(CategoryNotFoundException.class);

    }

    @Test
    @DisplayName("이미 삭제된 카테고리를 조회 요청 할 경우 예외 발생")
    public void 삭제된_카테고리_조회() {

        //given
        String parentName = "식당";
        Category parent = createCategory(parentName);
        Category child = createCategoryWithParent(parent.getId(), "한식");
        CategoryRequestDto requestDto = new CategoryRequestDto(child.getId());
        categoryService.delete(requestDto);
        entityManager.flush();
        entityManager.clear();
        //when, then
        assertThatThrownBy(() -> categoryService.findById(requestDto.getId()))
                .isInstanceOf(CategoryNotFoundException.class);
    }
}
