package com.handong.rebon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebclientConfig {

    @Bean
    public WebClient webClient() {
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                                                                  .codecs(configurer -> configurer.defaultCodecs()
                                                                                                  .maxInMemorySize(-1))
                                                                  .build();
        return WebClient.builder()
                        .exchangeStrategies(exchangeStrategies)
                        .build();
    }
}
