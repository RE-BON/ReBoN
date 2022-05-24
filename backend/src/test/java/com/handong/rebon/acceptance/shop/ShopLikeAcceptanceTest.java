package com.handong.rebon.acceptance.shop;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;

import com.handong.rebon.acceptance.AcceptanceTest;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.common.admin.AdminCategoryRegister;
import com.handong.rebon.common.admin.AdminShopRegister;
import com.handong.rebon.common.admin.AdminTagRegister;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.presentation.dto.response.ShopLikeResponse;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

class ShopLikeAcceptanceTest extends AcceptanceTest {

    @Autowired
    private AdminTagRegister adminTagRegister;

    @Autowired
    private AdminCategoryRegister adminCategoryRegister;

    @Autowired
    private AdminShopRegister adminShopRegister;

    @Autowired
    private EntityManager entityManager;

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
                Arrays.asList(tags.get("포항"), tags.get("양덕"))
        ));
    }

    @Test
    @DisplayName("가게 좋아요")
    void likeShop() {
        // given
        ExtractableResponse<Response> registerResponse = 회원가입();
        String token = extractedToken(registerResponse);
        Shop shop = shops.get("티타");

        // when
        ExtractableResponse<Response> response = 가게_좋아요(token, shop);
        ShopLikeResponse result = response.as(new TypeRef<>() {});

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result.isLike()).isTrue();
        assertThat(result.getLikeCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("가게 좋아요 취소")
    void unlikeShop() {
        // given
        ExtractableResponse<Response> registerResponse = 회원가입();
        String token = extractedToken(registerResponse);
        Shop shop = shops.get("티타");

        // when
        ExtractableResponse<Response> response1 = 가게_좋아요(token, shop);
        ShopLikeResponse result1 = response1.as(new TypeRef<>() {});

        ExtractableResponse<Response> response2 = 가게_좋아요_취소(token, shop);
        ShopLikeResponse result2 = response2.as(new TypeRef<>() {});

        // then
        assertThat(response2.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result1.getLikeCount()).isEqualTo(1);
        assertThat(result2.getLikeCount()).isEqualTo(0);
        assertThat(result2.isLike()).isFalse();
    }

    private ExtractableResponse<Response> 가게_좋아요(String token, Shop shop) {
        return RestAssured.given(getRequestSpecification())
                          .log().all()
                          .header("Authorization", "Bearer " + token)
                          .contentType(APPLICATION_JSON_VALUE)
                          .when()
                          .post("/api/shops/" + shop.getId() + "/like")
                          .then()
                          .log().all()
                          .extract();
    }

    private ExtractableResponse<Response> 가게_좋아요_취소(String token, Shop shop) {
        return RestAssured.given(getRequestSpecification())
                          .log().all()
                          .header("Authorization", "Bearer " + token)
                          .contentType(APPLICATION_JSON_VALUE)
                          .when()
                          .post("/api/shops/" + shop.getId() + "/unlike")
                          .then()
                          .log().all()
                          .extract();
    }
}
