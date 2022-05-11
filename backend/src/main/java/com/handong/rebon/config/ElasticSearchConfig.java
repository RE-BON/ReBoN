package com.handong.rebon.config;

import com.handong.rebon.tag.domain.repository.TagSearchRepository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import org.elasticsearch.client.RestHighLevelClient;

@EnableElasticsearchRepositories(basePackageClasses = {TagSearchRepository.class})
@Configuration
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                                                                     .connectedTo("localhost:9200")
                                                                     .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
