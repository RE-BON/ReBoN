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
import org.jetbrains.annotations.NotNull;

import static com.handong.rebon.acceptance.AcceptanceUtils.getRequestSpecification;
import static com.handong.rebon.acceptance.review.ReviewCreateAcceptanceTest.saveReview;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ReviewDeleteAcceptanceTest extends ReviewAcceptanceTest {

    @Test
    @DisplayName("로그인이 되어 있는 상태에서 review를 지울 수 있다.")
    void deleteReviewWithLogin() {
        //given
        String bearerToken = getBearerToken();

        ExtractableResponse<Response> reviewSaveResponse = saveTestReview(bearerToken);
        Long reviewId = getReviewId(reviewSaveResponse);
        //when
        ExtractableResponse<Response> response = deleteReview(reviewId, bearerToken);
        //then
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    @DisplayName("로그인이 되지 않은 상태에서 review를 지울 수 없다.")
    void deleteReviewWithoutLogin() {
        //given
        String bearerToken = getBearerToken();

        ExtractableResponse<Response> reviewSaveResponse = saveTestReview(bearerToken);
        Long reviewId = getReviewId(reviewSaveResponse);
        //when
        ExtractableResponse<Response> response = deleteReview(reviewId, null);
        //then
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());

    }

    public ExtractableResponse<Response> deleteReview(Long reviewId, String token) {
        RequestSpecification requestSpec = RestAssured.given(getRequestSpecification())
                                                      .log().all();
        if (!Objects.isNull(token)) {
            requestSpec.header("Authorization", token);
        }
        return requestSpec.contentType(APPLICATION_JSON_VALUE)
                          .when()
                          .delete("/api/reviews/" + reviewId)
                          .then()
                          .log().all()
                          .extract();
    }

    @NotNull
    private Long getReviewId(ExtractableResponse<Response> reviewSaveResponse) {
        String[] locations = reviewSaveResponse.header("location").split("/");
        Long reviewId = Long.parseLong(locations[locations.length - 1]);
        return reviewId;
    }

    private ExtractableResponse<Response> saveTestReview(String bearerToken) {
        ReviewRequest reviewRequest = new ReviewRequest("맛이 좋아요", "필수로 시키자", 5);
        Shop shop = shops.get("팜스발리");
        ExtractableResponse<Response> reviewSaveResponse = saveReview(reviewRequest, shop.getId(), bearerToken);
        return reviewSaveResponse;
    }

    @NotNull
    private String getBearerToken() {
        ExtractableResponse<Response> registerResponse = 회원가입();
        String token = extractedToken(registerResponse);
        String bearerToken = "Bearer " + token;
        return bearerToken;
    }
}
