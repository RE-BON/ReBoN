package com.handong.rebon;

import com.handong.rebon.tag.domain.repository.TagSearchRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = TagSearchRepository.class))
@SpringBootApplication
public class RebonApplication {

    public static void main(String[] args) {
        SpringApplication.run(RebonApplication.class, args);
    }
}
