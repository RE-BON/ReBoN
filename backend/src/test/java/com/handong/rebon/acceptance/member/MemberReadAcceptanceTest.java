package com.handong.rebon.acceptance.member;

import java.util.Objects;

import com.handong.rebon.acceptance.AcceptanceTest;
import com.handong.rebon.auth.application.AuthService;
import com.handong.rebon.member.presentation.dto.response.MemberReadResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.handong.rebon.acceptance.AcceptanceUtils.getRequestSpecification;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class MemberReadAcceptanceTest extends AcceptanceTest {

    @Autowired
    private AuthService authService;

    @Test
    @DisplayName("로그인이 되어 있는 상태에서 내 정보를 조회할 수 있다.")
    void findMemberInfoWithLogin() {
        //given
        ExtractableResponse<Response> registerResponse = 회원가입("test@gmail.com", "test");
        String token = extractedToken(registerResponse);
        String bearerToken = "Bearer " + token;

        //when
        ExtractableResponse<Response> response = getMemberInfo(bearerToken);
        MemberReadResponse result = response.as(MemberReadResponse.class);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result).extracting("email")
                          .isEqualTo("test@gmail.com");
        assertThat(result).extracting("nickName")
                          .isEqualTo("test");
        assertThat(result).extracting("agreed")
                          .isEqualTo(true);
    }

    @Test
    @DisplayName("로그인이 되어 있지 않은 상태에서 내 정보를 조회할 수 없다.")
    void findMemberInfoWithoutLogin() {
        //given
        ExtractableResponse<Response> registerResponse = 회원가입("test@gmail.com", "test");
        String token = extractedToken(registerResponse);
        String bearerToken = "Bearer " + token;

        //when
        ExtractableResponse<Response> response = getMemberInfo(null);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    public ExtractableResponse<Response> getMemberInfo(String token) {
        RequestSpecification requestSpec = RestAssured.given(getRequestSpecification())
                                                      .log().all();
        if (!Objects.isNull(token)) {
            requestSpec.header("Authorization", token);
        }
        return requestSpec.contentType(APPLICATION_JSON_VALUE)
                          .when()
                          .get("/api/members")
                          .then()
                          .log().all()
                          .extract();
    }
}