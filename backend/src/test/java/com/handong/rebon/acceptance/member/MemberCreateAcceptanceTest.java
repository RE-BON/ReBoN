package com.handong.rebon.acceptance.member;

import com.handong.rebon.acceptance.AcceptanceTest;
import com.handong.rebon.member.presentation.dto.request.MemberCreateRequest;

import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class MemberCreateAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("회원 가입을 한다.")
    void saveMember() {
        //given
        MemberCreateRequest memberCreateRequest = new MemberCreateRequest("test@gmail.com", "test", "google", true);

        //when
        ExtractableResponse<Response> response = saveMember(memberCreateRequest);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Authorization")).isNotNull();
    }

    private ExtractableResponse<Response> saveMember(MemberCreateRequest memberCreateRequest){
        return RestAssured.given(super.spec)
                .log().all()
                .contentType(APPLICATION_JSON_VALUE)
                .body(memberCreateRequest)
                .when()
                .post("/api/members")
                .then()
                .log().all()
                .extract();
    }
}
