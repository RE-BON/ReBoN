package com.handong.rebon.acceptance.category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.handong.rebon.acceptance.AcceptanceTest;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.category.presentation.dto.response.RootCategoryResponse;
import com.handong.rebon.common.admin.AdminCategoryRegister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static com.handong.rebon.acceptance.AcceptanceUtils.getRequestSpecification;
import static org.assertj.core.api.Assertions.assertThat;

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
        List<RootCategoryResponse> result = response.as(new TypeRef<>() {});
        //then

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

        assertThat(result)
                .extracting("name")
                .contains("식당", "숙소", "카페");

    }

    private ExtractableResponse<Response> 루트_카테고리_조회_요청() {
        return RestAssured.given(getRequestSpecification())
                          .log().all()
                          .when()
                          .get("/api/categories")
                          .then()
                          .log().all()
                          .extract();
    }
}
