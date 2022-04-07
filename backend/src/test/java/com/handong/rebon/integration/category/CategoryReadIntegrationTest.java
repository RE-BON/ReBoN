package com.handong.rebon.integration.category;

import java.util.Arrays;
import java.util.List;

import com.handong.rebon.category.application.dto.response.RootCategoryResponseDto;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.exception.category.CategoryIdException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CategoryReadIntegrationTest extends CategoryIntegrationTest {

    @Test
    @DisplayName("id에 따라 카테고리를 조회할 수 있다.")
    public void findCategoryById() {
        //given
        createCategory("중식");
        createCategory("양식");
        Category createdCategory = createCategory("한식");
        //when
        Category categoryId = categoryService.findById(createdCategory.getId());
        //then
        assertThat(categoryId).isEqualTo(createdCategory);
    }

    @Test
    @DisplayName("존재하지 않는 id의 카테고리를 요청하면 예외가 발생한다.")
    public void findCategoryByWrongId() {
        //given
        Long requestCategoryId = -1L;
        //when, then
        assertThatThrownBy(() -> categoryService.findById(requestCategoryId))
                .isInstanceOf(CategoryIdException.class);
    }

    @Test
    @DisplayName("subCategory들의 아이디에 따라 카테고리를 조회할수 있다.")
    public void findSubCategoryByIds() {
        //given
        Long parentId = createCategory("식당").getId();
        List<Long> subCategoryIds = Arrays.asList(
                createCategoryWithParent(parentId, "한식").getId(),
                createCategoryWithParent(parentId, "양식").getId(),
                createCategoryWithParent(parentId, "중식").getId()
        );
        //when
        List<Category> subCategories = categoryService.findSubCategoryByIds(subCategoryIds);

        //then
        assertThat(subCategories).extracting("name")
                                 .contains("한식", "양식", "중식");
    }

    @Test
    @DisplayName("부모가 없는 root 카테고리들과 그 자식 카테고리들을 조회할 수 있다.")
    public void findRootAndChildrenCategory() {
        //given
        Long parentId = createCategory("식당").getId();
        createCategoryWithParent(parentId, "한식");
        createCategoryWithParent(parentId, "양식");
        createCategoryWithParent(parentId, "중식");
        createCategoryWithParent(parentId, "프렌차이즈");
        createCategoryWithParent(parentId, "퓨전한식");
        Long parentId2 = createCategory("카페").getId();
        createCategoryWithParent(parentId2, "프렌차이즈");
        createCategoryWithParent(parentId2, "전통카페");
        Long parentId3 = createCategory("숙소").getId();
        createCategoryWithParent(parentId3, "호텔");
        createCategoryWithParent(parentId3, "펜션");
        createCategoryWithParent(parentId3, "모텔");
        createCategory("기타");
        //when
        List<RootCategoryResponseDto> rootCategories = categoryService.findRootCategoriesAndChildren();
        //then
        assertThat(rootCategories).hasSize(4)
                                  .extracting("name")
                                  .contains("식당", "카페", "숙소", "기타");
        assertThat(rootCategories.get(0).getChildren()).extracting("name")
                                                       .containsOnly("한식", "양식", "중식", "프렌차이즈", "퓨전한식");
        assertThat(rootCategories.get(1).getChildren()).extracting("name")
                                                       .containsOnly("프렌차이즈", "전통카페");
        assertThat(rootCategories.get(2).getChildren()).extracting("name")
                                                       .containsOnly("호텔", "펜션", "모텔");
    }

}
