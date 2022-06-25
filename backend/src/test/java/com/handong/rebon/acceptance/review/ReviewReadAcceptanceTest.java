package com.handong.rebon.acceptance.review;

import java.util.List;
import java.util.Objects;

import com.handong.rebon.review.presentation.dto.request.ReviewRequest;
import com.handong.rebon.review.presentation.dto.response.ReviewGetByMemberResponse;
import com.handong.rebon.review.presentation.dto.response.ReviewGetByShopResponse;
import com.handong.rebon.review.presentation.dto.response.TipGetByShopResponse;
import com.handong.rebon.shop.domain.Shop;

import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.jetbrains.annotations.NotNull;

import static com.handong.rebon.acceptance.AcceptanceUtils.getRequestSpecification;
import static com.handong.rebon.acceptance.review.ReviewCreateAcceptanceTest.saveReview;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ReviewReadAcceptanceTest extends ReviewAcceptanceTest {

    @Test
    @DisplayName("로그인이 되어 있는 상태에서 shop 리뷰를 가져올 수 있다.")
    void getReviewByShopWithLogin() {
        //given
        String bearerToken = getBearerToken();
        Shop shop = makeTestReview(bearerToken);

        //when
        ExtractableResponse<Response> response = getReviewByShop(shop.getId(), bearerToken);
        List<ReviewGetByShopResponse> result = response.jsonPath().getList(".", ReviewGetByShopResponse.class);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result).hasSize(1);

    }

    @Test
    @DisplayName("로그인이 되어 있지 않은 상태에서 shop 리뷰를 가져올 수 없다.")
    void getReviewByShopWithoutLogin() {
        //given
        String bearerToken = getBearerToken();
        Shop shop = makeTestReview(bearerToken);

        //when
        ExtractableResponse<Response> response = getReviewByShop(shop.getId(), null);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    @DisplayName("로그인이 되어 있는 상태에서 내가 쓴 리뷰를 가져올 수 있다.")
    void getReviewByMemberWithLogin() {
        //given
        String bearerToken = getBearerToken();
        makeTestReview(bearerToken);

        //when
        ExtractableResponse<Response> response = getReviewByMember(bearerToken);
        List<ReviewGetByMemberResponse> result = response.jsonPath().getList(".", ReviewGetByMemberResponse.class);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result).hasSize(1);
    }

    @Test
    @DisplayName("로그인이 되어 있지 않은 상태에서 내가 쓴 리뷰를 가져올 수 없다.")
    void getReviewByMemberWithoutLogin() {
        //given//when
        ExtractableResponse<Response> response = getReviewByMember(null);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    public ExtractableResponse<Response> getReviewByShop(Long shopId, String token) {
        RequestSpecification requestSpec = RestAssured.given(getRequestSpecification())
                                                      .log().all();
        if (!Objects.isNull(token)) {
            requestSpec.header("Authorization", token);
        }
        return requestSpec.contentType(APPLICATION_JSON_VALUE)
                          .when()
                          .get("/api/shops/" + shopId + "/reviews")
                          .then()
                          .log().all()
                          .extract();
    }

    public ExtractableResponse<Response> getReviewByMember(String token) {
        RequestSpecification requestSpec = RestAssured.given(getRequestSpecification())
                                                      .log().all();
        if (!Objects.isNull(token)) {
            requestSpec.header("Authorization", token);
        }
        return requestSpec.contentType(APPLICATION_JSON_VALUE)
                          .when()
                          .get("/api/my-reviews")
                          .then()
                          .log().all()
                          .extract();
    }

    @NotNull
    private String getBearerToken() {
        ExtractableResponse<Response> registerResponse = 회원가입("test@gmail.com", "test");
        String token = extractedToken(registerResponse);
        String bearerToken = "Bearer " + token;
        return bearerToken;
    }

    @NotNull
    private Shop makeTestReview(String bearerToken) {
        ReviewRequest reviewRequest = new ReviewRequest("맛이 좋아요", "필수로 시키자", 5);
        Shop shop = shops.get("팜스발리");
        saveReview(reviewRequest, shop.getId(), bearerToken);
        return shop;
    }

    @Test
    @DisplayName("로그인이 되어 있는 상태에서 나만의 꿀팁만 가져올 수 있다.")
    void getTipByShopWithLogin() {
        //given
        String bearerToken = getBearerToken();
        Shop shop = makeTestReview(bearerToken);

        //when
        ExtractableResponse<Response> response = getTipByShop(shop.getId(), bearerToken);
        List<TipGetByShopResponse> result = response.jsonPath().getList(".", TipGetByShopResponse.class);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result).hasSize(1);
    }

    @Test
    @DisplayName("로그인이 되어 있지 않은 상태에서 나만의 꿀팁을 가져올 수 없다.")
    void getTipByShopWithoutLogin() {
        //given
        String bearerToken = getBearerToken();
        Shop shop = makeTestReview(bearerToken);

        //when
        ExtractableResponse<Response> response = getTipByShop(shop.getId(), null);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    public ExtractableResponse<Response> getTipByShop(Long shopId, String token) {
        RequestSpecification requestSpec = RestAssured.given(getRequestSpecification())
                                                      .log().all();
        if (!Objects.isNull(token)) {
            requestSpec.header("Authorization", token);
        }
        return requestSpec.contentType(APPLICATION_JSON_VALUE)
                          .when()
                          .get("/api/shops/" + shopId + "/review/tips")
                          .then()
                          .log().all()
                          .extract();
    }
}
