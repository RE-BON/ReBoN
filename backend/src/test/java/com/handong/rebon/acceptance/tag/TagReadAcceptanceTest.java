package com.handong.rebon.acceptance.tag;

import com.handong.rebon.acceptance.AcceptanceTest;
import com.handong.rebon.acceptance.admin.AdminTagRegister;
import com.handong.rebon.tag.domain.Tag;
import com.handong.rebon.tag.presentation.dto.response.TagResponse;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TagReadAcceptanceTest extends AcceptanceTest {

    @Autowired
    private AdminTagRegister adminTagRegister;
    private Map<String, Tag> tags = new HashMap<>();

    @BeforeEach
    void setUp() {
        tags = adminTagRegister.register("북구", "남구", "흥해읍", "양덕동");
    }

    @Test
    @DisplayName("모든 태그 조회하기")
    public void getAllTags() {
        //given when
        ExtractableResponse<Response> response = 태그_조회_요청();
        List<TagResponse> result = response.as(new TypeRef<>() {
        });

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

        assertThat(result)
                .hasSize(4)
                .extracting("name")
                .contains("북구", "남구", "흥해읍", "양덕동");
    }

    private ExtractableResponse<Response> 태그_조회_요청() {
        return RestAssured.given(super.spec)
                .log().all()
                .when()
                .get("/api/tags")
                .then()
                .log().all()
                .extract();
    }
}
