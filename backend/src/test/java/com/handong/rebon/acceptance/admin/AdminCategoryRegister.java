package com.handong.rebon.acceptance.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.category.domain.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@Component
@ActiveProfiles("test")
public class AdminCategoryRegister {

    @Autowired
    private CategoryRepository categoryRepository;

    public Map<String, Category> registerMain(String... names) {
        List<Category> categories = new ArrayList<>();
        for (String name : names) {
            categories.add(new Category(name));
        }
        categoryRepository.saveAll(categories);
        return categories.stream()
                         .collect(Collectors.toMap(Category::getName, Function.identity()));
    }

    public Map<String, Category> registerSubs(Category parent, String... names) {
        List<Category> categories = new ArrayList<>();
        for (String name : names) {
            categories.add(new Category(name, parent));
        }
        categoryRepository.saveAll(categories);
        return categories.stream()
                         .collect(Collectors.toMap(Category::getName, Function.identity()));
    }
}
