package com.handong.rebon.category.application;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.category.application.dto.response.RootCategoryResponseDto;
import com.handong.rebon.exception.category.CategoryExistException;
import com.handong.rebon.exception.category.CategoryIdException;
import com.handong.rebon.exception.category.CategoryNoParentException;
import com.handong.rebon.category.application.dto.request.CategoryRequestDto;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.category.domain.repository.CategoryRepository;

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

        Category savedCategory = categoryRepository.save(newCategory);
        return savedCategory.getId();
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

    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                                 .orElseThrow(CategoryIdException::new);
    }

    public List<Category> findSubCategoryByIds(List<Long> subCategories) {
        return categoryRepository.findAllById(subCategories);
    }

    public List<RootCategoryResponseDto> findRootCategoriesAndChildren() {
        return categoryRepository.findRootCategories()
                                 .stream()
                                 .map(RootCategoryResponseDto::new)
                                 .collect(Collectors.toList());
    }

}
