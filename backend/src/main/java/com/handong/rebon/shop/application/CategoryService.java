package com.handong.rebon.shop.application;

import com.handong.rebon.exception.category.CategoryExistException;
import com.handong.rebon.exception.category.CategoryNoParentException;
import com.handong.rebon.shop.application.dto.CategoryRequestDto;
import com.handong.rebon.shop.domain.category.Category;
import com.handong.rebon.shop.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long create(String categoryName) {
        checkCategoryExist(categoryName);

        Category newCategory = new Category(categoryName);

        categoryRepository.save(newCategory);
        return newCategory.getId();
    }

    @Transactional
    public Long create(CategoryRequestDto categoryRequestDto) {

        Category parent = categoryRepository.findById(categoryRequestDto.getParentId())
                                            .orElseThrow(CategoryNoParentException::new);

        String categoryName = categoryRequestDto.getName();

        Category newCategory = new Category(categoryName);

        parent.addChildCategory(newCategory);
        Category savedCategory = categoryRepository.save(newCategory);

        return savedCategory.getId();
    }

    private void checkCategoryExist(String name) {
        if (categoryRepository.existsByName(name)) {
            throw new CategoryExistException();
        }
    }

}
