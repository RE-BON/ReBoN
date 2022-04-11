package com.handong.rebon.integration.category;

import com.handong.rebon.category.application.dto.request.CategoryRequestDto;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.exception.category.CategoryAlreadyDeletedException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CategoryDeleteIntegrationTest extends CategoryIntegrationTest {

    @Test
    @DisplayName("자식 카테고리를 삭제한다.")
    public void 자식_카테고리_삭제() {

        //given
        String parentName = "식당";
        String childName = "한식";
        Category parent = createCategory(parentName);
        Category child = createCategoryWithParent(parent.getId(), childName);

        //when
        categoryService.delete(new CategoryRequestDto(child.getId()));

        //then
        assertThat(child).extracting("deleted")
                         .isEqualTo(true);
        assertThat(child).extracting("parent")
                         .isNull();
        assertThat(parent.getChildren()).doesNotContain(child);
    }

    @Test
    @DisplayName("루트 카테고리를 삭제하면 자식 카테고리들까지 모두 삭제된다.")
    public void 루트_카테고리_삭제() {

        //given
        String parentName = "식당";
        Category parent = createCategory(parentName);
        createCategoryWithParent(parent.getId(), "한식");
        createCategoryWithParent(parent.getId(), "중식");
        createCategoryWithParent(parent.getId(), "양식");

        //when
        categoryService.delete(new CategoryRequestDto(parent.getId()));

        //then
        assertThat(parent).extracting("deleted")
                          .isEqualTo(true);
        assertThat(parent.getChildren()).extracting("deleted")
                                        .containsOnly(true);
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

        //when, then
        assertThatThrownBy(()->categoryService.delete(requestDto))
                .isInstanceOf(CategoryAlreadyDeletedException.class);

    }

}
