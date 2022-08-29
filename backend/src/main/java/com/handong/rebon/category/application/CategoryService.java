package com.handong.rebon.category.application;

import java.util.*;
import java.util.stream.Collectors;

import com.handong.rebon.category.application.dto.CategoryDtoAssembler;
import com.handong.rebon.category.application.dto.request.CategoryRequestDto;
import com.handong.rebon.category.application.dto.request.CategoryUpdateRequestDto;
import com.handong.rebon.category.application.dto.response.RootCategoryResponseDto;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.category.domain.repository.CategoryRepository;
import com.handong.rebon.exception.category.CategoryExistException;
import com.handong.rebon.exception.category.CategoryNotFoundException;
import com.handong.rebon.exception.category.CategoryParentIdNullException;
import com.handong.rebon.exception.category.NoSuchCategoryException;
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
    public Long create(CategoryRequestDto categoryRequestDto) {
        if (Objects.isNull(categoryRequestDto.getId())) {
            return this.create(categoryRequestDto.getName());
        }
        Category parent = this.findById(categoryRequestDto.getId());

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

    private void checkChildCategoryExist(Category parent, String name) {
        if (categoryRepository.existsByParentAndName(parent, name)) {
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
    public void update(CategoryUpdateRequestDto categoryUpdateRequestDto) {
        Category category = this.findById(categoryUpdateRequestDto.getId());
        if (category.isParentCategory()) {
            checkCategoryExist(categoryUpdateRequestDto.getName());
            category.updateCategoryName(categoryUpdateRequestDto.getName());
            return;
        }

        if (Objects.isNull(categoryUpdateRequestDto.getParentId())) {
            throw new CategoryParentIdNullException();
        }
        checkChildCategoryExist(category.getParent(), categoryUpdateRequestDto.getName());
        Category parentCategory = this.findById(categoryUpdateRequestDto.getParentId());
        category.update(parentCategory, categoryUpdateRequestDto.getName());
    }

    @Transactional(readOnly = true)
    public Category findByName(String name) {
        return categoryRepository.findByName(name)
                                 .orElseThrow(NoSuchCategoryException::new);
    }

    @Transactional
    public List<Category> getSubCategories(Category mainCategory, List<String> categories) {
        List<Category> subCategories = new ArrayList<>();
        if (Objects.isNull(categories) || categories.isEmpty()) {
            return subCategories;
        }

        categories.stream()
                  .map(s -> s.split(","))
                  .flatMap(Arrays::stream)
                  .forEach(name -> {
                      Optional<Category> category = categoryRepository.findByName(name);

                      if (category.isEmpty()) {
                          Category newCategory = categoryRepository.save(new Category(name, mainCategory));
                          subCategories.add(newCategory);
                      } else {
                          subCategories.add(category.get());
                      }
                  });

        return subCategories;
    }
}
