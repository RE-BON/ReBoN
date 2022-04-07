package com.handong.rebon.integration.category;

import java.util.List;
import java.util.Optional;

import com.handong.rebon.category.application.dto.request.CategoryCreateRequestDto;
import com.handong.rebon.category.application.dto.request.CategoryRequestDto;
import com.handong.rebon.category.application.dto.response.RootCategoryResponseDto;
import com.handong.rebon.category.domain.Category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CategoryDeleteIntegrationTest extends CategoryIntegrationTest{
    @Test
    @DisplayName("자식 카테고리를 삭제한다.")
    public void 자식_카테고리_삭제(){
        //given

        String parentName = "식당";
        String createdName = "테스트한식";
        Long parentId = categoryService.create(parentName);

//        CategoryCreateRequestDto categoryCreateRequestDto = CategoryCreateRequestDto.builder()
//                                                                                    .parentId(parentId)
//                                                                                    .name(createdName)
//                                                                                    .build();
        //when
//        Long id = categoryService.create(categoryCreateRequestDto);
//        categoryService.delete(parentId);

        Category category = categoryRepository.findById(parentId).orElseThrow();
        System.out.println(category.getName());
        System.out.println(category.isDeleted());
//        System.out.println(category.getChildren().get(0).getName());
//        System.out.println(category.getChildren().get(0).isDeleted());

        //when

        //then
    }

    @Test
    @DisplayName("루트 카테고리를 삭제한다.")
    public void 루트_카테고리_삭제(){
        //given


        //when

        //then
    }

    @Test
    @DisplayName("이미 삭제된 카테고리 일 경우 예외 발생")
    public void 삭제된_카테고리_삭제(){
        //given


        //when

        //then
    }

    @Test
    @DisplayName("존재하지 않는 카테고리 삭제시 예외 발생")
    public void 존재하지않는_카테고리_삭제(){
        //given


        //when

        //then
    }
}
