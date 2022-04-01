package com.handong.rebon.integration.category;

import com.handong.rebon.exception.category.CategoryExistException;
import com.handong.rebon.exception.category.CategoryNoParentException;
import com.handong.rebon.category.application.CategoryService;
import com.handong.rebon.category.application.dto.CategoryRequestDto;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.category.domain.repository.CategoryRepository;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class CategoryIntegrationTest {
    @Autowired
    protected CategoryService categoryService;

    @Autowired
    protected CategoryRepository categoryRepository;


}
