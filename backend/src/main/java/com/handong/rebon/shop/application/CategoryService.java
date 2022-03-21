package com.handong.rebon.shop.application;

import com.handong.rebon.exception.category.CategoryExistException;
import com.handong.rebon.exception.category.CategoryNoParentException;
import com.handong.rebon.shop.application.dto.CategoryRequestDto;
import com.handong.rebon.shop.domain.category.Categories;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Long create(String name) {
        isCategoryExist(name);

        Category newCategory = Category.builder()
                                       .name(name)
                                       .children(new Categories())
                                       .build();
        categoryRepository.save(newCategory);
        return newCategory.getId();
    }

    public Long create(CategoryRequestDto categoryRequestDto) {

        Category parent = categoryRepository.findById(categoryRequestDto.getParentId())
                                            .orElseThrow(CategoryNoParentException::new);

        String categoryName = categoryRequestDto.getName();

        Category newCategory = Category.builder()
                                       .name(categoryName)
                                       .children(new Categories())
                                       .build();

        parent.addChildCategory(newCategory);
        categoryRepository.save(newCategory);

        return newCategory.getId();
    }

    private void isCategoryExist(String name) {
        if(categoryRepository.existsByName(name)) throw new CategoryExistException();
    }

}
