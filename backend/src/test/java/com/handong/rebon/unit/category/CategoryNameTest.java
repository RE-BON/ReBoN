package com.handong.rebon.unit.category;

import com.handong.rebon.exception.category.CategoryNameException;
import com.handong.rebon.exception.category.CategoryNoParentException;
import com.handong.rebon.shop.domain.category.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CategoryNameTest {
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("카테고리의 이름이 비어있거나 공백으로 들어온 경우 등록할 수 없다.")
    void blankCategoryName(String name){
        Assertions.assertThatThrownBy(() -> Category.builder().name(name).build())
                .isInstanceOf(CategoryNameException.class);
    }
}
