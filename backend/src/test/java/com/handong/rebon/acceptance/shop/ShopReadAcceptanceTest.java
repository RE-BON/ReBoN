package com.handong.rebon.acceptance.shop;

import java.time.LocalTime;
import java.util.*;

import com.handong.rebon.acceptance.AcceptanceTest;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.common.admin.AdminCategoryRegister;
import com.handong.rebon.common.admin.AdminShopRegister;
import com.handong.rebon.common.admin.AdminTagRegister;
import com.handong.rebon.exception.dto.ExceptionResponse;
import com.handong.rebon.shop.application.dto.response.ShopSimpleResponseDto;
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
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.handong.rebon.acceptance.AcceptanceUtils.getRequestSpecification;
import static com.handong.rebon.acceptance.shop.ShopLikeAcceptanceTest.가게_좋아요;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ShopReadAcceptanceTest extends AcceptanceTest {

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
        ExtractableResponse<Response> response = 가게_리스트_조회_요청(포항.getId(), 식당.getId(), Collections.emptyList(), false);
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
        ExtractableResponse<Response> response
                = 가게_리스트_조회_요청(포항.getId(), 식당.getId(), Collections.singletonList(양식.getId()), false);
        List<ShopSimpleResponse> result = response.jsonPath().getList(".", ShopSimpleResponse.class);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result).hasSize(4);
    }

    @Test
    @DisplayName("포항에 있는 모든 분식이나 양식 식당 조회하기")
    void searchSchoolFoodOrWesternRestaurantInPohang() {
        // given
        ExtractableResponse<Response> registerResponse = 회원가입("test@gmail.com", "test");
        String token = extractedToken(registerResponse);
        Shop shop = shops.get("팜스발리");
        Tag 포항 = tags.get("포항");
        Category 식당 = categories.get("식당");
        Category 분식 = categories.get("분식");
        Category 양식 = categories.get("양식");

        // when
        가게_좋아요(token, shop);
        ExtractableResponse<Response> response
                = 로그인_유저_가게_리스트_조회_요청(token, 포항.getId(), 식당.getId(), Arrays.asList(분식.getId(), 양식.getId()), false);
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
        ExtractableResponse<Response> response = 가게_리스트_조회_요청(양덕.getId(), 카페.getId(), Collections.emptyList(), false);
        List<ShopSimpleResponse> result = response.jsonPath().getList(".", ShopSimpleResponse.class);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result).hasSize(3);
    }

    @Test
    @DisplayName("카페 단일 조회")
    void showCafeDetail() {
        // given
        Long shopId = shops.get("티타").getId();

        // when
        ExtractableResponse<Response> response = 단일_가게_조회_요청(shopId);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("삭제되지 않은 카페 리스트 조회")
    void findAllDeletedFalse() {
        // given
        Category 카페 = categories.get("카페");
        Tag 양덕 = tags.get("양덕");
        adminShopRegister.delete(shops.get("티타"));

        // when
        ExtractableResponse<Response> response = 가게_리스트_조회_요청(양덕.getId(), 카페.getId(), Collections.emptyList(), false);
        List<ShopSimpleResponseDto> result = response.as(new TypeRef<>() {});

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result).extracting("name")
                          .doesNotContain("티타");
    }

    @Test
    @DisplayName("삭제된 카페 조회")
    void findDeleteCafe() {
        // given
        Shop shop = shops.get("티타");
        adminShopRegister.delete(shop);

        // when
        ExtractableResponse<Response> response = 단일_가게_조회_요청(shop.getId());
        ExceptionResponse result = response.as(new TypeRef<>() {});

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(result.getMessage()).isEqualTo("존재하지 않는 가게입니다.");
    }

    @Test
    @DisplayName("영업중인 가게 조회")
    void searchOpenShops() {
        // given
        Tag 양덕 = tags.get("양덕");
        Category 카페 = categories.get("카페");

        adminShopRegister.CafeRegisterWithMenu(
                "카페1",
                categories.get("카페"),
                Collections.singletonList(categories.get("개인카페")),
                Arrays.asList(tags.get("포항"), tags.get("양덕")),
                LocalTime.MIN,
                LocalTime.MAX
        );
        adminShopRegister.CafeRegisterWithMenu(
                "카페2",
                categories.get("카페"),
                Collections.singletonList(categories.get("개인카페")),
                Arrays.asList(tags.get("포항"), tags.get("양덕")),
                LocalTime.MIN,
                LocalTime.MAX
        );

        // when
        ExtractableResponse<Response> response = 가게_리스트_조회_요청(양덕.getId(), 카페.getId(), Collections.emptyList(), true);
        List<ShopSimpleResponse> result = response.jsonPath().getList(".", ShopSimpleResponse.class);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result).hasSize(2);
    }

    public static ExtractableResponse<Response> 로그인_유저_가게_리스트_조회_요청(String token, Long tag, Long category, List<Long> subs, boolean open) {
        RequestSpecification specification = RestAssured.given(getRequestSpecification())
                                                        .log().all()
                                                        .header("Authorization", "Bearer " + token);
        return 가게_리스트_조회_요청(tag, category, subs, open, specification);
    }

    public static ExtractableResponse<Response> 가게_리스트_조회_요청(Long tag, Long category, List<Long> subs, boolean open) {
        RequestSpecification specification = RestAssured.given(getRequestSpecification())
                                                        .log().all();
        return 가게_리스트_조회_요청(tag, category, subs, open, specification);
    }

    private static ExtractableResponse<Response> 가게_리스트_조회_요청(Long tag, Long category, List<Long> subs, boolean open, RequestSpecification specification) {
        return specification
                .contentType(APPLICATION_JSON_VALUE)
                .queryParam("tag", tag)
                .queryParam("category", category)
                .queryParam("subCategories", subs)
                .queryParam("open", open)
                .when()
                .get("/api/shops")
                .then()
                .log().all()
                .extract();
    }

    private ExtractableResponse<Response> 단일_가게_조회_요청(Long shopId) {
        return RestAssured.given(getRequestSpecification())
                          .log().all()
                          .contentType(APPLICATION_JSON_VALUE)
                          .pathParam("id", shopId)
                          .when()
                          .get("/api/shops/{id}")
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
        shops.put("티타", adminShopRegister.CafeRegisterWithMenu(
                "티타",
                categories.get("카페"),
                Collections.singletonList(categories.get("개인카페")),
                Arrays.asList(tags.get("포항"), tags.get("양덕")),
                null,
                null
        ));
    }
}
