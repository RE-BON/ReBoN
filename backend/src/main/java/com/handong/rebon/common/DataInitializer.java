package com.handong.rebon.common;

import java.util.List;
import java.util.regex.Pattern;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.category.domain.repository.CategoryRepository;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@Profile("prod")
public class DataInitializer implements ApplicationRunner {
    public static Pattern HOUR_PATTERN = Pattern.compile("([0-9]+:[0-9]+~[0-9]+:[0-9]+)");

    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Category 식당 = new Category("식당");
        Category 카페 = new Category("카페");
        Category 숙소 = new Category("숙소");
        categoryRepository.saveAll(List.of(식당, 카페, 숙소));
    }
}
