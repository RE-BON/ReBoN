//package com.handong.rebon.config;
//
//import com.handong.rebon.acceptance.tag.TagElasticSearchAcceptanceTest;
//import com.handong.rebon.tag.domain.repository.TagSearchRepository;
//
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
//import org.elasticsearch.client.RestHighLevelClient;
//
//@EnableElasticsearchRepositories(basePackageClasses = {TagSearchRepository.class})
//@TestConfiguration
//public class TestElasticSearchConfig extends AbstractElasticsearchConfiguration {
//
//    @Override
//    public RestHighLevelClient elasticsearchClient() {
//        ClientConfiguration clientConfiguration =
//                ClientConfiguration.builder()
//                                   .connectedTo(TagElasticSearchAcceptanceTest.CONTAINER.getHost() +
//                                           ":" + TagElasticSearchAcceptanceTest.CONTAINER.getMappedPort(9200))
//                                   .build();
//        return RestClients.create(clientConfiguration).rest();
//    }
//}
