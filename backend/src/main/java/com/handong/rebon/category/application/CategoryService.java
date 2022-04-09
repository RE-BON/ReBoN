package com.handong.rebon.category.application;

import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.category.application.dto.CategoryDtoAssembler;
import com.handong.rebon.category.application.dto.request.CategoryRequestDto;
import com.handong.rebon.category.application.dto.response.RootCategoryResponseDto;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.category.domain.repository.CategoryRepository;
import com.handong.rebon.exception.category.CategoryExistException;
import com.handong.rebon.exception.category.CategoryNoParentException;
import com.handong.rebon.exception.category.NoSuchCategoryException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
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

    @Transactional(readOnly = true)
    public List<RootCategoryResponseDto> findRootCategoriesAndChildren() {
        List<Category> categories = categoryRepository.findRootCategories();
        return CategoryDtoAssembler.rootCategoryResponseDtos(categories);
    }

    @Transactional(readOnly = true)
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                                 .orElseThrow(NoSuchCategoryException::new);
    }

    @Transactional(readOnly = true)
    public List<Category> findAllContainIds(List<Long> subCategories) {
        return subCategories.stream()
                            .map(this::findById)
                            .collect(Collectors.toList());
    }
}
