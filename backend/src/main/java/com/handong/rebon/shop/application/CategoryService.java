package com.handong.rebon.shop.application;

import com.handong.rebon.exception.category.CategoryExistException;
import com.handong.rebon.exception.category.CategoryNoParentException;
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

        Category.CategoryBuilder builder = Category.builder()
                .name(name);
        Category newCategory = builder.build();
        categoryRepository.save(newCategory);
        return newCategory.getId();
    }

    public Long add(String name, String parentName){
        isCategoryExist(name);

        Category parent = categoryRepository.findByName(parentName)
                .orElseThrow(CategoryNoParentException::new);

        Category newCategory = Category.builder()
                .name(name)
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
