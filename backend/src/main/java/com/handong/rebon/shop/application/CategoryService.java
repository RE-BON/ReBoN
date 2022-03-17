package com.handong.rebon.shop.application;

import com.handong.rebon.exception.category.CategoryExistException;
import com.handong.rebon.exception.category.CategoryNoParentException;
import com.handong.rebon.shop.application.dto.CategoryRequestDto;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Long add(String name){
        isCategoryExist(name);

        Category newCategory = Category.builder()
                .name(name)
                .build();
        categoryRepository.save(newCategory);
        return newCategory.getId();
    }

    public Long add(CategoryRequestDto categoryRequestDto){
        String categoryName = categoryRequestDto.getName();
        isCategoryExist(categoryName);

        Category parent = categoryRepository.findByName(categoryRequestDto.getParent())
                .orElseThrow(CategoryNoParentException::new);

        Category newCategory = Category.builder()
                .name(categoryName)
                .parent(parent)
                .build();

        categoryRepository.save(new Category());

        return newCategory.getId();
    }

    private void isCategoryExist(String name){
        Optional<Category> category = categoryRepository.findByName(name);
        if(category.isPresent()) throw new CategoryExistException();
    }

}
