package com.handong.rebon.config;

import com.handong.rebon.common.ImageUploader;
import com.handong.rebon.common.fakebean.TestImageUploader;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class InfrastructureTestConfig {

    @Bean
    public ImageUploader imageUploader() {
        return new TestImageUploader();
    }
}
