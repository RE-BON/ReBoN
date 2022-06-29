package com.handong.rebon.unit.category.domain;

import java.util.List;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.exception.category.CategoryAlreadyDeletedException;
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

    @Test
    @DisplayName("카테고리를 삭제할 수 있다.")
    public void deleteCategory() {
        // given
        Category category = new Category("식당");
        // when
        category.delete();
        // then
        assertThat(category).extracting("deleted").isEqualTo(true);
    }

    @Test
    @DisplayName("부모 카테고리를 삭제하면 모든 자식 카테고리가 삭제된다.")
    public void deleteParentCategory() {
        // given
        Category parentCategory = new Category("식당");
        Category childCategory = new Category("프렌차이즈");
        Category childCategory2 = new Category("중식");
        parentCategory.addChildCategory(childCategory);
        parentCategory.addChildCategory(childCategory2);
        // when
        parentCategory.delete();
        // then
        assertThat(parentCategory).extracting("deleted").isEqualTo(true);
        assertThat(childCategory).extracting("deleted").isEqualTo(true);
        assertThat(childCategory2).extracting("deleted").isEqualTo(true);
    }

    @Test
    @DisplayName("이미 삭제한 카테고리를 다시 삭제할 수 없다.")
    public void deleteAgain() {
        // given
        Category category = new Category("식당");
        category.delete();
        // when, then
        assertThatThrownBy(category::delete)
                .isInstanceOf(CategoryAlreadyDeletedException.class);
    }

    @Test
    @DisplayName("카테고리의 이름을 수정할 수 있다.")
    public void 부모_카테고리_수정() {
        // given
        Category category = new Category("식당");

        // when
        category.updateCategoryName("카페");

        // then
        assertThat(category).extracting("name")
                            .isEqualTo("카페");
    }

    @Test
    @DisplayName("카테고리의 이름을 수정시 공백일 경우 예외 발생.")
    public void 부모_카테고리_수정_공백() {
        // given
        Category category = new Category("식당");

        // when, then
        assertThatThrownBy(() -> category.updateCategoryName(""))
                .isInstanceOf(CategoryNameException.class);

    }

    @Test
    @DisplayName("자식 카테고리의 부모와 이름을 수정할 수 있다.")
    public void 자식_카테고리_부모_이름_수정() {
        // given
        Category parentCategory = new Category("식당");
        Category childCategory = new Category("한식");
        parentCategory.addChildCategory(childCategory);
        Category newParentCategory = new Category("카페");

        // when
        childCategory.update(newParentCategory, "전통차");

        // then
        assertThat(childCategory).extracting("parent")
                                 .isEqualTo(newParentCategory);

        assertThat(parentCategory.getChildren()).extracting("name")
                                                .doesNotContain("전통차");

        assertThat(newParentCategory.getChildren()).extracting("name")
                                                   .containsOnly("전통차");


        assertThat(childCategory).extracting("name")
                                 .isEqualTo("전통차");
    }
}
