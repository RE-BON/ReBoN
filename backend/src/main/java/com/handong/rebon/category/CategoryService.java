package com.handong.rebon.category;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // 카테고리 담당이 아니라 Shop 메서드가 잘 돌아가는지만 확인하기 위해 임시로 만든것.
    // 카테고리 파트랑 머지할 때 삭제하면 될듯
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId).get();
    }

    public List<Category> findAll(List<Long> subCategories) {
        return subCategories.stream()
                            .map(t -> categoryRepository.findById(t).get())
                            .collect(Collectors.toList());
    }
}
