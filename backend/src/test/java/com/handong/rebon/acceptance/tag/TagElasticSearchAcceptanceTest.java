package com.handong.rebon.acceptance.tag;

import java.util.List;

import com.handong.rebon.acceptance.AcceptanceTest;
import com.handong.rebon.common.admin.AdminTagRegister;
import com.handong.rebon.config.TestElasticSearchConfig;
import com.handong.rebon.tag.domain.repository.TagSearchRepository;
import com.handong.rebon.tag.presentation.dto.response.TagResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@Import(TestElasticSearchConfig.class)
public class TagElasticSearchAcceptanceTest extends AcceptanceTest {

    @Autowired
    private AdminTagRegister adminTagRegister;

    @Autowired
    private TagSearchRepository tagSearchRepository;

    @Container
    public static GenericContainer CONTAINER = new GenericContainer<>(
            new ImageFromDockerfile()
                    .withDockerfileFromBuilder(it -> it.from("docker.elastic.co/elasticsearch/elasticsearch:7.16.2")
                                                       .run("/usr/share/elasticsearch/bin/elasticsearch-plugin install https://github.com/netcrazy/elasticsearch-jaso-analyzer/releases/download/v7.16.2/jaso-analyzer-plugin-7.16.2-plugin.zip")
                                                       .build()))
            .withExposedPorts(9200)
            .withEnv("discovery.type", "single-node");

    @BeforeEach
    void setUp() {
        tagSearchRepository.deleteAll();
        adminTagRegister.register("북구", "남구", "흥해읍", "양덕동", "한동대");
    }

    @Test
    @DisplayName("자동완성 - 해당 문자를 포함하는 글자를 포함하는 태그 목록 반환하기")
    void autoComplete() {
        // given
        String keyword = "ㅎ";

        // when
        ExtractableResponse<Response> response = 자동완성_요청(keyword);
        List<TagResponse> result = response.as(new TypeRef<>() {
        });

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("자동완성 - 초성검색")
    void chosungSearch() {
        // given
        String keyword = "ㅎㄷㄷ";

        // when
        ExtractableResponse<Response> response = 자동완성_요청(keyword);
        List<TagResponse> result = response.as(new TypeRef<>() {
        });

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result).hasSize(1);
    }

    private ExtractableResponse<Response> 자동완성_요청(String keyword) {
        return RestAssured.given()
                          .log().all()
                          .queryParam("keyword", keyword)
                          .when()
                          .get("/api/tags/search")
                          .then()
                          .log().all()
                          .extract();
    }
}
