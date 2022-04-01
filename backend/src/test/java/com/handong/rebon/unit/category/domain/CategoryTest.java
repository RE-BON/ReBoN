package com.handong.rebon.unit.category.domain;

import java.util.List;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.exception.category.CategoryExistException;
import com.handong.rebon.exception.category.CategoryNameException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CategoryTest {
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("카테고리의 이름이 비어있거나 공백으로 들어온 경우 등록할 수 없다.")
    void blankCategoryName(String name) {
        assertThatThrownBy(() -> new Category(name))
                .isInstanceOf(CategoryNameException.class);
    }

    @Test
    @DisplayName("부모가 있는 카테고리를 생성할 수 있다.")
    void createCategoryWithParent() {
        // given
        Category parentCategory = new Category("식당");
        Category childCategory = new Category("한식");
        Category childCategory2 = new Category("양식");
        // when
        parentCategory.addChildCategory(childCategory);
        parentCategory.addChildCategory(childCategory2);
        List<Category> childCategoires = parentCategory.getChildren();
        // then
        assertThat(parentCategory).extracting("name")
                                  .isEqualTo("식당");
        assertThat(childCategoires).extracting("name")
                                   .contains("한식", "양식");
        assertThat(childCategoires).extracting("parent")
                                   .contains(parentCategory);

    }

    @Test
    @DisplayName("다른 부모의 카테고리는 중복된 이름으로 생성될 수 있다.")
    public void createWithDifferentParent() {
        // given
        Category parentCategory = new Category("식당");
        Category parentCategory2 = new Category("카페");
        Category childCategory = new Category("프렌차이즈");
        Category childCategory2 = new Category("프렌차이즈");
        // when
        parentCategory.addChildCategory(childCategory);
        parentCategory2.addChildCategory(childCategory2);
        // then
        assertThat(childCategory.getName()).isEqualTo(childCategory2.getName());
        assertThat(childCategory).extracting("parent")
                                 .extracting("name")
                                 .isEqualTo("식당");
        assertThat(childCategory2).extracting("parent")
                                  .extracting("name")
                                  .isEqualTo("카페");

    }

    @Test
    @DisplayName("같은 부모의 카테고리는 중복된 이름으로 생성될 수 없다.")
    public void createWithSameParent() {
        // given
        Category parentCategory = new Category("식당");
        Category childCategory = new Category("프렌차이즈");
        Category childCategory2 = new Category("프렌차이즈");
        // when
        parentCategory.addChildCategory(childCategory);

        // then
        assertThatThrownBy(() -> parentCategory.addChildCategory(childCategory2))
                .isInstanceOf(CategoryExistException.class);

    }


}
