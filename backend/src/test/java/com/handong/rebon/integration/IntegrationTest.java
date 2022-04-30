package com.handong.rebon.integration;

import com.handong.rebon.config.InfrastructureTestConfig;
import com.handong.rebon.util.DatabaseCleaner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import org.junit.jupiter.api.BeforeEach;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@Import(InfrastructureTestConfig.class)
public class IntegrationTest {

    @Autowired
    private DatabaseCleaner cleaner;

    @BeforeEach
    void init() {
        cleaner.execute();
    }
}
