package com.handong.rebon.acceptance.review;

import java.util.Objects;

import com.handong.rebon.review.presentation.dto.request.ReviewRequest;
import com.handong.rebon.shop.domain.Shop;

import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.assertj.core.api.Assertions;

import static com.handong.rebon.acceptance.AcceptanceUtils.getRequestSpecification;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ReviewCreateAcceptanceTest extends ReviewAcceptanceTest {

    @Test
    @DisplayName("로그인이 되어 있는 상태에서 리뷰를 작성한다.")
    void createReviewWithLogin() {
        //given
        ExtractableResponse<Response> registerResponse = 회원가입();
        String token = extractedToken(registerResponse);
        ReviewRequest reviewRequest = new ReviewRequest("맛이 좋아요", "필수로 시키자", 5);
        Shop shop = shops.get("팜스발리");
        String bearerToken = "Bearer " + token;

        //when
        ExtractableResponse<Response> response = saveReview(reviewRequest, shop.getId(), bearerToken);

        //then
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

    }

    @Test
    @DisplayName("로그인이 되어있지 않은 상태에서 리뷰를 작성하지 못한다.")
    void createReviewWithoutLogin() {
        //given
        ReviewRequest reviewRequest = new ReviewRequest("맛이 좋아요", "족발은 필수로 시키자", 5);
        Shop shop = shops.get("팜스발리");

        //when
        ExtractableResponse<Response> response = saveReview(reviewRequest, shop.getId(), null);

        //then
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    private ExtractableResponse<Response> saveReview(ReviewRequest reviewRequest, Long shopId, String token) {
        RequestSpecification requestSpec = RestAssured.given(getRequestSpecification())
                                                      .log().all();
        if (!Objects.isNull(token)) {
            requestSpec.header("Authorization", token);
        }
        return requestSpec.contentType(APPLICATION_JSON_VALUE)
                          .body(reviewRequest)
                          .when()
                          .post("/api/shops/" + shopId + "/reviews")
                          .then()
                          .log().all()
                          .extract();
    }
}
