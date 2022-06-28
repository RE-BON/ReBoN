package com.handong.rebon.acceptance.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.handong.rebon.acceptance.AcceptanceTest;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.common.admin.AdminCategoryRegister;
import com.handong.rebon.common.admin.AdminShopRegister;
import com.handong.rebon.common.admin.AdminTagRegister;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.tag.domain.Tag;
import com.handong.rebon.tag.presentation.dto.response.TagResponse;

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
import static com.handong.rebon.acceptance.shop.ShopReadAcceptanceTest.가게_리스트_조회_요청;
import static org.assertj.core.api.Assertions.assertThat;

public class TagReadAcceptanceTest extends AcceptanceTest {

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
        tags = adminTagRegister.register("북구", "남구", "흥해읍", "양덕동");
        categories = adminCategoryRegister.registerMain("식당", "숙소", "카페");
        categories.putAll(adminCategoryRegister.registerSubs(categories.get("식당"), "한식", "분식", "일식", "양식"));
        categories.putAll(adminCategoryRegister.registerSubs(categories.get("카페"), "프랜차이즈", "개인카페"));
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

    @Test
    @DisplayName("인기 태그 조회")
    void getTopTags() {
        // given
        Tag 북구 = tags.get("북구");
        Tag 남구 = tags.get("남구");
        Tag 흥해읍 = tags.get("흥해읍");
        Tag 양덕동 = tags.get("양덕동");
        Category 식당 = categories.get("식당");
        Category 한식 = categories.get("한식");

        가게_리스트_조회_요청(북구.getId(), 식당.getId(), List.of(한식.getId()), false);
        가게_리스트_조회_요청(북구.getId(), 식당.getId(), List.of(한식.getId()), false);
        가게_리스트_조회_요청(북구.getId(), 식당.getId(), List.of(한식.getId()), false);
        가게_리스트_조회_요청(북구.getId(), 식당.getId(), List.of(한식.getId()), false);
        가게_리스트_조회_요청(남구.getId(), 식당.getId(), List.of(한식.getId()), false);
        가게_리스트_조회_요청(남구.getId(), 식당.getId(), List.of(한식.getId()), false);
        가게_리스트_조회_요청(남구.getId(), 식당.getId(), List.of(한식.getId()), false);
        가게_리스트_조회_요청(흥해읍.getId(), 식당.getId(), List.of(한식.getId()), false);
        가게_리스트_조회_요청(흥해읍.getId(), 식당.getId(), List.of(한식.getId()), false);
        가게_리스트_조회_요청(양덕동.getId(), 식당.getId(), List.of(한식.getId()), false);

        // when
        ExtractableResponse<Response> response = 인기_태그_조회();
        List<TagResponse> result = response.as(new TypeRef<>() {
        });

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result.get(0)).extracting("name").isEqualTo(북구.getName());
        assertThat(result.get(1)).extracting("name").isEqualTo(남구.getName());
        assertThat(result.get(2)).extracting("name").isEqualTo(흥해읍.getName());
        assertThat(result.get(3)).extracting("name").isEqualTo(양덕동.getName());
    }

    private ExtractableResponse<Response> 태그_조회_요청() {
        return RestAssured.given(getRequestSpecification())
                          .log().all()
                          .when()
                          .get("/api/tags")
                          .then()
                          .log().all()
                          .extract();
    }

    private ExtractableResponse<Response> 인기_태그_조회() {
        return RestAssured.given(getRequestSpecification())
                          .log().all()
                          .when()
                          .get("/api/tags/top")
                          .then()
                          .log().all()
                          .extract();
    }
}
