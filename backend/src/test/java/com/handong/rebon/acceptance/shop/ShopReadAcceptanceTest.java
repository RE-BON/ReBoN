package com.handong.rebon.acceptance.shop;

import java.util.*;

import com.handong.rebon.acceptance.AcceptanceTest;
import com.handong.rebon.acceptance.admin.AdminCategoryRegister;
import com.handong.rebon.acceptance.admin.AdminShopRegister;
import com.handong.rebon.acceptance.admin.AdminTagRegister;
import com.handong.rebon.category.Category;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.content.ShopImage;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.presentation.dto.response.ShopSimpleResponse;
import com.handong.rebon.tag.domain.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

class ShopReadAcceptanceTest extends AcceptanceTest {

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
        initShops();
    }

    @Test
    @DisplayName("포항에 있는 모든 식당 조회하기")
    void searchRestaurantInPohang() {
        // given
        Tag 포항 = tags.get("포항");
        Category 식당 = categories.get("식당");

        // when
        ExtractableResponse<Response> response = 가게_리스트_조회_요청(포항.getId(), 식당.getId(), Collections.emptyList());
        List<ShopSimpleResponse> result = response.jsonPath().getList(".", ShopSimpleResponse.class);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result).hasSize(6);
    }

    @Test
    @DisplayName("포항에 있는 모든 양식 식당 조회하기")
    void searchWesternRestaurantInPohang() {
        // given
        Tag 포항 = tags.get("포항");
        Category 식당 = categories.get("식당");
        Category 양식 = categories.get("양식");

        // when
        ExtractableResponse<Response> response = 가게_리스트_조회_요청(포항.getId(), 식당.getId(), Collections.singletonList(양식.getId()));
        List<ShopSimpleResponse> result = response.jsonPath().getList(".", ShopSimpleResponse.class);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result).hasSize(4);
    }

    @Test
    @DisplayName("포항에 있는 모든 분식이나 양식 식당 조회하기")
    void searchSchoolFoodOrWesternRestaurantInPohang() {
        // given
        Tag 포항 = tags.get("포항");
        Category 식당 = categories.get("식당");
        Category 분식 = categories.get("분식");
        Category 양식 = categories.get("양식");

        // when
        ExtractableResponse<Response> response = 가게_리스트_조회_요청(포항.getId(), 식당.getId(), Arrays.asList(분식.getId(), 양식.getId()));
        List<ShopSimpleResponse> result = response.jsonPath().getList(".", ShopSimpleResponse.class);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result).hasSize(5);
    }

    @Test
    @DisplayName("양덕에 있는 모든 카페 조회하기")
    void searchCafeInYangdok() {
        // given
        Tag 양덕 = tags.get("양덕");
        Category 카페 = categories.get("카페");

        // when
        ExtractableResponse<Response> response = 가게_리스트_조회_요청(양덕.getId(), 카페.getId(), Collections.emptyList());
        List<ShopSimpleResponse> result = response.jsonPath().getList(".", ShopSimpleResponse.class);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result).hasSize(2);
    }

    private ExtractableResponse<Response> 가게_리스트_조회_요청(Long tag, Long category, List<Long> subs) {
        return RestAssured.given()
                          .log().all()
                          .contentType(APPLICATION_JSON_VALUE)
                          .queryParam("tag", tag)
                          .queryParam("category", category)
                          .queryParam("subCategories", subs)
                          .when()
                          .get("/api/shops")
                          .then()
                          .log().all()
                          .extract();
    }

    private void initShops() {
        shops.clear();
        initRestaurant();
        initCafe();
    }

    private void initRestaurant() {
        shops.put("팜스발리", adminShopRegister.simpleRegister(
                "팜스발리",
                categories.get("식당"),
                Arrays.asList(categories.get("양식"), categories.get("분식")),
                Arrays.asList(tags.get("포항"), tags.get("한동대")),
                new ShopImages(Collections.singletonList(new ShopImage("url1", true)))
        ));
        shops.put("도토리", adminShopRegister.simpleRegister(
                "도토리",
                categories.get("식당"),
                Collections.singletonList(categories.get("한식")),
                Arrays.asList(tags.get("포항"), tags.get("양덕")),
                new ShopImages(Collections.singletonList(new ShopImage("url2", true)))
        ));
        shops.put("한동대_학관", adminShopRegister.simpleRegister(
                "한동대_학관",
                categories.get("식당"),
                Arrays.asList(categories.get("한식"), categories.get("일식"), categories.get("양식")),
                Arrays.asList(tags.get("포항"), tags.get("한동대")),
                new ShopImages(Collections.singletonList(new ShopImage("url3", true)))
        ));
        shops.put("인브리즈", adminShopRegister.simpleRegister(
                "인브리즈",
                categories.get("식당"),
                Collections.singletonList(categories.get("양식")),
                Arrays.asList(tags.get("포항"), tags.get("한동대"), tags.get("양덕")),
                new ShopImages(Collections.singletonList(new ShopImage("url4", true)))
        ));
        shops.put("미스터피자", adminShopRegister.simpleRegister(
                "미스터피자",
                categories.get("식당"),
                Collections.singletonList(categories.get("양식")),
                Arrays.asList(tags.get("포항"), tags.get("양덕")),
                new ShopImages(Collections.singletonList(new ShopImage("url5", true)))
        ));
        shops.put("엽기떡볶이", adminShopRegister.simpleRegister(
                "엽기떡볶이",
                categories.get("식당"),
                Collections.singletonList(categories.get("분식")),
                Arrays.asList(tags.get("포항"), tags.get("양덕")),
                new ShopImages(Collections.singletonList(new ShopImage("url6", true)))
        ));
    }

    private void initCafe() {
        shops.put("이디야", adminShopRegister.simpleRegister(
                "이디야",
                categories.get("카페"),
                Collections.singletonList(categories.get("프랜차이즈")),
                Arrays.asList(tags.get("포항"), tags.get("한동대")),
                new ShopImages(Collections.singletonList(new ShopImage("url3", true)))
        ));
        shops.put("스타벅스", adminShopRegister.simpleRegister(
                "스타벅스",
                categories.get("카페"),
                Collections.singletonList(categories.get("프랜차이즈")),
                Arrays.asList(tags.get("포항"), tags.get("양덕"), tags.get("영일대")),
                new ShopImages(Collections.singletonList(new ShopImage("url4", true)))
        ));
        shops.put("커피유야", adminShopRegister.simpleRegister(
                "커피유야",
                categories.get("카페"),
                Collections.singletonList(categories.get("개인카페")),
                Arrays.asList(tags.get("포항"), tags.get("양덕")),
                new ShopImages(Collections.singletonList(new ShopImage("url5", true)))
        ));
    }
}
