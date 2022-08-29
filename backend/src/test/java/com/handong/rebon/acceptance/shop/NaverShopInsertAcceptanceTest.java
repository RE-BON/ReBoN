package com.handong.rebon.acceptance.shop;

import com.handong.rebon.acceptance.AcceptanceTest;
import com.handong.rebon.common.admin.AdminCategoryRegister;
import com.handong.rebon.shop.presentation.dto.request.NaverShopRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import org.assertj.core.api.Assertions;

import static com.handong.rebon.acceptance.AcceptanceUtils.getRequestSpecification;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class NaverShopInsertAcceptanceTest extends AcceptanceTest {

    @Autowired
    private AdminCategoryRegister adminCategoryRegister;

    @BeforeEach
    void setUp() {
        adminCategoryRegister.registerMain("식당", "숙소", "카페");
    }

    @Test
    @DisplayName("네이버 가게 등록")
    void naverShopInsert() {
        // given
        NaverShopRequest shopRequest = new NaverShopRequest("포항", 10, 1);

        // when
        ExtractableResponse<Response> response = 네이버_가게_등록(shopRequest);

        // then
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("네이버 가게 등록 - 없는 페이지")
    void naverShopInsertNotExist() {
        // given
        NaverShopRequest shopRequest = new NaverShopRequest("포항", 10, 100);

        // when
        ExtractableResponse<Response> response = 네이버_가게_등록(shopRequest);

        // then
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    private ExtractableResponse<Response> 네이버_가게_등록(NaverShopRequest naverShopRequest) {
        return RestAssured.given(getRequestSpecification())
                          .log().all()
                          .contentType(APPLICATION_JSON_VALUE)
                          .body(naverShopRequest)
                          .when()
                          .post("/api/shops/naver")
                          .then()
                          .log().all()
                          .extract();
    }
}
