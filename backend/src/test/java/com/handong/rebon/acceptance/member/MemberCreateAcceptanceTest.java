package com.handong.rebon.acceptance.member;

import com.handong.rebon.acceptance.AcceptanceTest;
import com.handong.rebon.member.presentation.dto.request.DuplicateRequest;
import com.handong.rebon.member.presentation.dto.request.MemberCreateRequest;

import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static com.handong.rebon.acceptance.AcceptanceUtils.getRequestSpecification;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class MemberCreateAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("회원 가입을 한다.")
    void saveMember() {
        //given
        MemberCreateRequest memberCreateRequest = new MemberCreateRequest(
                TEST_EMAIL,
                TEST_NICKNAME,
                TEST_OAUTH_PROVIDER,
                true
        );

        //when
        ExtractableResponse<Response> response = saveMember(memberCreateRequest);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.body()).isNotNull();
    }

    @Test
    @DisplayName("존재하지 않는 닉네임 중복 체크 요청")
    void checkDidNotDuplicateNickname() {
        //given
        String nickname = "ReBoN";
        DuplicateRequest duplicateRequest = new DuplicateRequest(nickname);
        //when
        ExtractableResponse<Response> response = checkDuplicateNickname(duplicateRequest);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("존재하는 닉네임 중복 체크 요청")
    void checkDuplicatedNickname() {
        //given
        String nickname = "test";
        DuplicateRequest duplicateRequest = new DuplicateRequest(nickname);
        saveMember(new MemberCreateRequest("test@gmail.com", nickname, "google", true));

        //when
        ExtractableResponse<Response> response = checkDuplicateNickname(duplicateRequest);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    private ExtractableResponse<Response> checkDuplicateNickname(DuplicateRequest nickname) {
        return RestAssured.given(getRequestSpecification())
                          .log().all()
                          .contentType(APPLICATION_JSON_VALUE)
                          .body(nickname)
                          .when()
                          .post("/api/members/nickname/check-duplicate")
                          .then()
                          .log().all()
                          .extract();
    }

    public static ExtractableResponse<Response> saveMember(MemberCreateRequest memberCreateRequest) {
        return RestAssured.given(getRequestSpecification())
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
