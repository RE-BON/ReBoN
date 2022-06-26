package com.handong.rebon.acceptance.review;

import com.handong.rebon.review.presentation.dto.request.ReviewRequest;
import com.handong.rebon.review.presentation.dto.response.ReviewEmpathizeResponse;

import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static com.handong.rebon.acceptance.AcceptanceUtils.getRequestSpecification;
import static com.handong.rebon.acceptance.review.ReviewCreateAcceptanceTest.saveReview;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ReviewEmpathyAcceptanceTest extends ReviewAcceptanceTest {

    @Test
    @DisplayName("리뷰 공감하기")
    public void empathizeReview() {
        //given
        ExtractableResponse<Response> regitserResponse = 회원가입("gomster96@gmail.com", "gomster");
        ReviewRequest reviewRequest = new ReviewRequest("맛있어요", "피자는 벌집모양으로!", 5);
        String token = extractedToken(regitserResponse);
        String bearerToken = "Bearer " + token;
        ExtractableResponse<Response> reviewCreateResponse = saveReview(
                reviewRequest, shops.get("팜스발리").getId(), bearerToken);

        String[] locations = reviewCreateResponse.header("location").split("/");
        long reviewId = Long.parseLong(locations[locations.length - 1]);
        regitserResponse = 회원가입("test@gmail.com", "test");
        token = extractedToken(regitserResponse);

        //when
        ExtractableResponse<Response> response = 리뷰_공감하기(token, reviewId);
        ReviewEmpathizeResponse result = response.as(new TypeRef<>() {});
        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result.isEmpathy()).isTrue();
        assertThat(result.getEmpathizeCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("리뷰 공감하기 취소")
    public void unEmpathizeReview() {
        //given
        ExtractableResponse<Response> regitserResponse = 회원가입("gomster96@gmail.com", "gomster");
        ReviewRequest reviewRequest = new ReviewRequest("맛있어요", "피자는 벌집모양으로!", 5);
        String token = extractedToken(regitserResponse);
        String bearerToken = "Bearer " + token;
        ExtractableResponse<Response> reviewCreateResponse = saveReview(
                reviewRequest, shops.get("팜스발리").getId(), bearerToken);

        String[] locations = reviewCreateResponse.header("location").split("/");
        long reviewId = Long.parseLong(locations[locations.length - 1]);
        regitserResponse = 회원가입("test@gmail.com", "test");
        token = extractedToken(regitserResponse);


        //when
        ExtractableResponse<Response> response1 = 리뷰_공감하기(token, reviewId);
        ReviewEmpathizeResponse result1 = response1.as(new TypeRef<>() {});

        ExtractableResponse<Response> response2 = 리뷰_공감하기_취소(token, reviewId);
        ReviewEmpathizeResponse result2 = response2.as(new TypeRef<>() {});
        //then
        assertThat(result1.getEmpathizeCount()).isEqualTo(1);
        assertThat(response2.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result2.isEmpathy()).isFalse();
        assertThat(result2.getEmpathizeCount()).isEqualTo(0);
    }

    private ExtractableResponse<Response> 리뷰_공감하기(String token, Long reviewId) {
        return RestAssured.given(getRequestSpecification())
                          .log().all()
                          .header("Authorization", "Bearer " + token)
                          .contentType(APPLICATION_JSON_VALUE)
                          .when()
                          .post("/api/reviews/" + reviewId + "/empathize")
                          .then()
                          .log().all()
                          .extract();
    }

    private ExtractableResponse<Response> 리뷰_공감하기_취소(String token, Long reviewId) {
        return RestAssured.given(getRequestSpecification())
                          .log().all()
                          .header("Authorization", "Bearer " + token)
                          .contentType(APPLICATION_JSON_VALUE)
                          .when()
                          .post("/api/reviews/" + reviewId + "/unempathize")
                          .then()
                          .log().all()
                          .extract();
    }

}
