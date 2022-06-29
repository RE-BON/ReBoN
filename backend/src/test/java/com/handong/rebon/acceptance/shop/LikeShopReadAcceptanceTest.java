package com.handong.rebon.acceptance.shop;

import java.time.LocalTime;
import java.util.*;

import com.handong.rebon.acceptance.AcceptanceTest;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.common.admin.AdminCategoryRegister;
import com.handong.rebon.common.admin.AdminShopRegister;
import com.handong.rebon.common.admin.AdminTagRegister;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.presentation.dto.response.ShopSimpleResponse;
import com.handong.rebon.tag.domain.Tag;

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
import static com.handong.rebon.acceptance.shop.ShopLikeAcceptanceTest.가게_좋아요;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


public class LikeShopReadAcceptanceTest extends AcceptanceTest {
    @Autowired
    private AdminTagRegister adminTagRegister;

    @Autowired
    private AdminCategoryRegister adminCategoryRegister;

    @Autowired
    private AdminShopRegister adminShopRegister;

    private Map<String, Tag> tags = new HashMap<>();
    private Map<String, Category> categories = new HashMap<>();
    private Map<String, Shop> shops = new HashMap<>();

    @BeforeEach
    void setUp() {
        tags = adminTagRegister.register("포항", "영일대", "한동대", "양덕");
        categories = adminCategoryRegister.registerMain("식당", "숙소", "카페");
        categories.putAll(adminCategoryRegister.registerSubs(categories.get("식당"), "한식", "분식", "일식", "양식"));
        categories.putAll(adminCategoryRegister.registerSubs(categories.get("카페"), "프랜차이즈", "개인카페"));
        shops.clear();
        shops.put("티타", adminShopRegister.CafeRegisterWithMenu(
                "티타",
                categories.get("카페"),
                Collections.singletonList(categories.get("개인카페")),
                Arrays.asList(tags.get("포항"), tags.get("양덕")),
                LocalTime.MIN,
                LocalTime.MAX
        ));
        shops.put("설빙", adminShopRegister.CafeRegisterWithMenu(
                "설빙",
                categories.get("카페"),
                Collections.singletonList(categories.get("프랜차이즈")),
                Arrays.asList(tags.get("포항"), tags.get("양덕")),
                LocalTime.MIN,
                LocalTime.MAX
        ));
    }

    @Test
    @DisplayName("내가 찜한 가게 리스트 조회")
    public void getLikeShops() {
        //given
        ExtractableResponse<Response> registerResponse = 회원가입("test@gmail.com", "test");
        String token = extractedToken(registerResponse);
        가게_좋아요(token, shops.get("티타"));
        가게_좋아요(token, shops.get("설빙"));
        Long categoryId = categories.get("카페").getId();
        //when
        ExtractableResponse<Response> response = 찜한_가게_조회(token, categoryId);
        List<ShopSimpleResponse> result = response.as(new TypeRef<>() {});
        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).extracting("name")
                          .containsOnly("티타", "설빙");

    }

    private ExtractableResponse<Response> 찜한_가게_조회(String token, Long categoryId) {
        return RestAssured.given(getRequestSpecification())
                          .log().all()
                          .header("Authorization", "Bearer " + token)
                          .contentType(APPLICATION_JSON_VALUE)
                          .when()
                          .get("/api/shops/likes?categoryId=" + categoryId)
                          .then()
                          .log().all()
                          .extract();
    }
}
