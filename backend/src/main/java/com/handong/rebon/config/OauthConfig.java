package com.handong.rebon.config;

import java.util.Map;

import com.handong.rebon.auth.domain.OauthProperties;
import com.handong.rebon.auth.domain.OauthProvider;
import com.handong.rebon.auth.infrastructure.OauthHandler;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(OauthProperties.class)
public class OauthConfig {

    private final OauthProperties properties;

    @Bean
    public OauthHandler oauthHandler() {
        Map<String, OauthProvider> providers = properties.getOauthProviders();
        return new OauthHandler(providers);
    }
}
