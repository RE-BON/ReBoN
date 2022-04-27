package com.handong.rebon.category.application;

import java.util.List;

import com.handong.rebon.category.application.dto.CategoryDtoAssembler;
import com.handong.rebon.category.application.dto.request.CategoryCreateRequestDto;
import com.handong.rebon.category.application.dto.request.CategoryRequestDto;
import com.handong.rebon.category.application.dto.response.RootCategoryResponseDto;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.category.domain.repository.CategoryRepository;
import com.handong.rebon.common.BaseEntity;
import com.handong.rebon.exception.category.CategoryAlreadyDeletedException;
import com.handong.rebon.exception.category.CategoryExistException;
import com.handong.rebon.exception.category.CategoryNotFoundException;
import com.handong.rebon.exception.category.CategoryNoParentException;

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
    public Long create(CategoryCreateRequestDto categoryCreateRequestDto) {

        Category parent = categoryRepository.findById(categoryCreateRequestDto.getParentId())
                                            .orElseThrow(CategoryNoParentException::new);

        String categoryName = categoryCreateRequestDto.getName();

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
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                                 .orElseThrow(CategoryNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<Category> findSubCategoryByIds(List<Long> subCategories) {
        return categoryRepository.findAllById(subCategories);
    }

    @Transactional(readOnly = true)
    public List<RootCategoryResponseDto> findRootCategoriesAndChildren() {
        List<Category> categories = categoryRepository.findRootCategories();
        return CategoryDtoAssembler.rootCategoryResponseDtos(categories);
    }

    @Transactional
    public void delete(CategoryRequestDto categoryRequestDto){
        Category category = this.findById(categoryRequestDto.getId());
        if(category.isDeleted()) throw new CategoryAlreadyDeletedException();
        category.getChildren().forEach(BaseEntity::deleteContent);
        category.delete();
    }
}
