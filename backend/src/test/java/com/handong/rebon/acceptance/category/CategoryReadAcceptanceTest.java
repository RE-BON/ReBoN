package com.handong.rebon.acceptance.category;

import java.util.HashMap;
import java.util.Map;

import com.handong.rebon.acceptance.AcceptanceTest;
import com.handong.rebon.acceptance.admin.AdminCategoryRegister;
import com.handong.rebon.category.domain.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import org.assertj.core.api.Assertions;

public class CategoryReadAcceptanceTest extends AcceptanceTest {
    @Autowired
    private AdminCategoryRegister adminCategoryRegister;
    private Map<String, Category> categories = new HashMap<>();

    @BeforeEach
    void setUp() {
        categories = adminCategoryRegister.registerMain("식당", "숙소", "카페");
        categories.putAll(adminCategoryRegister.registerSubs(categories.get("식당"), "한식", "분식", "일식", "양식", "퓨전한식"));
        categories.putAll(adminCategoryRegister.registerSubs(categories.get("카페"), "프랜차이즈", "개인카페", "전통카페"));
        categories.putAll(adminCategoryRegister.registerSubs(categories.get("숙소"), "호텔", "모텔", "펜션"));
    }

    @Test
    @DisplayName("현재 있는 모든 루트 카테고리와 그 자식카테고리 조회하기")
    public void getAllRootAndChildrent() {
        //given when
        ExtractableResponse<Response> response = 루트_카테고리_조회_요청();
        //then

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.body().jsonPath().getList("name"))
                  .hasSize(3)
                  .containsOnly("식당", "숙소", "카페");

        // 질문을 위해 남겨둡니다.
//        Assertions.assertThat(response.body().jsonPath().getList("children.name[0]"))
//                .hasSize(5)
//                .containsOnly("한식", "분식", "일식", "양식", "퓨전한식");
//        Assertions.assertThat(response.body().jsonPath().getList("children.name[1]"))
//                  .hasSize(3)
//                  .containsOnly("호텔", "모텔", "펜션");
//
//        Assertions.assertThat(response.body().jsonPath().getList("children.name[2]"))
//                  .hasSize(3)
//                  .containsOnly("프랜차이즈", "개인카페", "전통카페");

    }

    private ExtractableResponse<Response> 루트_카테고리_조회_요청() {
        return RestAssured.given(super.spec)
                          .log().all()
                          .when()
                          .get("/api/categories")
                          .then()
                          .log().all()
                          .extract();
    }
}
