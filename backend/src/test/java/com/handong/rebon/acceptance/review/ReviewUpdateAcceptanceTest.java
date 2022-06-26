package com.handong.rebon.acceptance.review;

import java.util.Objects;

import com.handong.rebon.review.presentation.dto.request.ReviewRequest;
import com.handong.rebon.shop.domain.Shop;

import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.jetbrains.annotations.NotNull;

import static com.handong.rebon.acceptance.AcceptanceUtils.getRequestSpecification;
import static com.handong.rebon.acceptance.review.ReviewCreateAcceptanceTest.saveReview;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ReviewUpdateAcceptanceTest extends ReviewAcceptanceTest {

    @Test
    @DisplayName("리뷰 소유자는 리뷰를 수정할 수 있다.")
    void reviewUpdateByReviewAuthor() {
        //given
        ExtractableResponse<Response> registerResponse = 회원가입("test@gmail.com", "test");
        String token = extractedToken(registerResponse);
        String bearerToken = "Bearer " + token;

        ExtractableResponse<Response> saveResponse = saveTestReview(bearerToken);
        Long reviewId = getReviewId(saveResponse);

        ReviewRequest updateReviewRequest = new ReviewRequest("맛있다!!", "굳굳", 4);

        //when
        ExtractableResponse<Response> response = updateReview(updateReviewRequest, reviewId, bearerToken);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    @DisplayName("리뷰 소유자가 아니면, 리뷰를 수정할 수 없다.")
    void reviewUpdateByNotReviewAuthor() {
        //given
        ExtractableResponse<Response> registerResponse1 = 회원가입("test1@gmail.com", "test1");
        String token1 = extractedToken(registerResponse1);
        String bearerToken1 = "Bearer " + token1;

        ExtractableResponse<Response> saveResponse = saveTestReview(bearerToken1);
        Long reviewId = getReviewId(saveResponse);

        ExtractableResponse<Response> registerResponse2 = 회원가입("test2@gmail.com", "test2");
        String token2 = extractedToken(registerResponse2);
        String bearerToken2 = "Bearer " + token2;

        ReviewRequest updateReviewRequest = new ReviewRequest("맛있다!!", "굳굳", 4);

        //when
        ExtractableResponse<Response> response = updateReview(updateReviewRequest, reviewId, bearerToken2);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    @Test
    @DisplayName("리뷰 수정 내용 중, content가 없으면 수정할 수 없다.")
    void reviewUpdateByEmptyReviewContent() {
        //given
        ExtractableResponse<Response> registerResponse = 회원가입("test@gmail.com", "test");
        String token = extractedToken(registerResponse);
        String bearerToken = "Bearer " + token;

        ExtractableResponse<Response> saveResponse = saveTestReview(bearerToken);
        Long reviewId = getReviewId(saveResponse);

        ReviewRequest updateReviewRequest = new ReviewRequest("", "굳굳", 4);

        //when
        ExtractableResponse<Response> response = updateReview(updateReviewRequest, reviewId, bearerToken);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 6})
    @DisplayName("리뷰 수정 내용 중, score가 0보다 작거나 같거나 5보다 크면 수정할 수 없다. ")
    void reviewUpdateByBelowZeroExcessFive(int score) {
        //given
        ExtractableResponse<Response> registerResponse = 회원가입("test@gmail.com", "test");
        String token = extractedToken(registerResponse);
        String bearerToken = "Bearer " + token;

        ExtractableResponse<Response> saveResponse = saveTestReview(bearerToken);
        Long reviewId = getReviewId(saveResponse);

        ReviewRequest updateReviewRequest = new ReviewRequest("", "굳굳", score);

        //when
        ExtractableResponse<Response> response = updateReview(updateReviewRequest, reviewId, bearerToken);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @NotNull
    private Long getReviewId(ExtractableResponse<Response> saveResponse) {
        String[] locations = saveResponse.header("location").split("/");
        Long reviewId = Long.parseLong(locations[locations.length - 1]);
        return reviewId;
    }

    private ExtractableResponse<Response> saveTestReview(String bearerToken) {
        ReviewRequest reviewRequest = new ReviewRequest("맛이 좋아요", "필수로 시키자", 5);
        Shop shop = shops.get("팜스발리");
        ExtractableResponse<Response> saveResponse = saveReview(reviewRequest, shop.getId(), bearerToken);
        return saveResponse;
    }

    public static ExtractableResponse<Response> updateReview(ReviewRequest reviewRequest, Long reviewId, String token) {
        RequestSpecification requestSpec = RestAssured.given(getRequestSpecification())
                                                      .log().all();
        if (!Objects.isNull(token)) {
            requestSpec.header("Authorization", token);
        }
        return requestSpec.contentType(APPLICATION_JSON_VALUE)
                          .body(reviewRequest)
                          .when()
                          .patch("/api/reviews/" + reviewId)
                          .then()
                          .log().all()
                          .extract();
    }

}
