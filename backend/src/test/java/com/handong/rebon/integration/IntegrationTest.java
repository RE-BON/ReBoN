package com.handong.rebon.integration;

import com.handong.rebon.config.InfrastructureTestConfig;
import com.handong.rebon.config.NotUseElasticSearchConfig;
import com.handong.rebon.tag.domain.repository.TagSearchRepository;
import com.handong.rebon.util.DatabaseCleaner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import org.junit.jupiter.api.BeforeEach;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@Import({InfrastructureTestConfig.class, NotUseElasticSearchConfig.class})
public class IntegrationTest {

    @Autowired
    private DatabaseCleaner cleaner;

    @MockBean
    private ElasticsearchTemplate elasticsearchTemplate;

    @MockBean
    private TagSearchRepository tagSearchRepository;

    @BeforeEach
    void init() {
        cleaner.execute();
    }
}
