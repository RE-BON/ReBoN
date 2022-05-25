package com.handong.rebon.category.application;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.handong.rebon.category.application.dto.CategoryDtoAssembler;
import com.handong.rebon.category.application.dto.request.CategoryRequestDto;
import com.handong.rebon.category.application.dto.response.RootCategoryResponseDto;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.category.domain.repository.CategoryRepository;
import com.handong.rebon.exception.category.CategoryExistException;
import com.handong.rebon.exception.category.CategoryNotFoundException;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.repository.ShopRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ShopRepository shopRepository;

    @Transactional
    public Long create(String categoryName) {
        checkCategoryExist(categoryName);

        Category newCategory = new Category(categoryName);

        Category savedCategory = categoryRepository.save(newCategory);
        return savedCategory.getId();
    }

    @Transactional
    public Long create(CategoryRequestDto categoryCreateRequestDto) {
        if (Objects.isNull(categoryCreateRequestDto.getId())) {
            return this.create(categoryCreateRequestDto.getName());
        }
        Category parent = this.findById(categoryCreateRequestDto.getId());

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

    private void checkChildCategoryExist(Long parentId, String name) {
        if (categoryRepository.existsByParentAndName(parentId, name)) {
            throw new CategoryExistException();
        }
    }

    @Transactional
    public void delete(Long categoryId) {
        Category category = categoryRepository.findCategoryWithChildren(categoryId)
                                              .orElseThrow(CategoryNotFoundException::new);
        if (category.isParentCategory()) {
            List<Shop> shops = shopRepository.findByCategory(category);
            shopRepository.deleteAll(shops);
        }
        category.delete();
    }

    @Transactional(readOnly = true)
    public List<RootCategoryResponseDto> findRootCategoriesAndChildren() {
        List<Category> categories = categoryRepository.findRootCategories();
        return CategoryDtoAssembler.rootCategoryResponseDtos(categories);
    }

    @Transactional(readOnly = true)
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                                 .orElseThrow(CategoryNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<Category> findAllContainIds(List<Long> subCategories) {
        return subCategories.stream()
                            .map(this::findById)
                            .collect(Collectors.toList());
    }

    @Transactional
    public void update(CategoryRequestDto categoryRequestDto) {
        Category category = this.findById(categoryRequestDto.getId());
        if (category.isParentCategory()) {
            checkCategoryExist(categoryRequestDto.getName());
            category.updateCategoryName(categoryRequestDto.getName());
            return;
        }
        checkChildCategoryExist(category.getParent().getId(), categoryRequestDto.getName());
        category.updateCategoryName(categoryRequestDto.getName());
    }
}
