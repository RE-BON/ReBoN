package com.handong.rebon.acceptance.member;

import java.util.Objects;

import com.handong.rebon.acceptance.AcceptanceTest;
import com.handong.rebon.auth.application.AuthService;
import com.handong.rebon.auth.domain.LoginMember;
import com.handong.rebon.member.presentation.dto.request.MemberCreateRequest;
import com.handong.rebon.member.presentation.dto.request.MemberUpdateRequest;

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

public class MemberUpdateAcceptanceTest extends AcceptanceTest {
    @Autowired
    private AuthService authService;

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
        //when
        ExtractableResponse<Response> response = checkDuplicateNickname(nickname);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("존재하는 닉네임 중복 체크 요청")
    void checkDuplicatedNickname() {
        //given
        String nickname = "test";
        saveMember(new MemberCreateRequest("test@gmail.com", nickname, "google", true));

        //when
        ExtractableResponse<Response> response = checkDuplicateNickname(nickname);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    private ExtractableResponse<Response> checkDuplicateNickname(String nickname) {
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

    @Test
    @DisplayName("로그인인 상태에서는 내 정보를 수정할 수 있다.")
    void updateMemberInfoWithLogin() {
        //given
        ExtractableResponse<Response> registerResponse = 회원가입("test@gmail.com", "test");
        String token = extractedToken(registerResponse);
        String bearerToken = "Bearer " + token;

        LoginMember loginMember = authService.findMemberByToken(token);

        MemberUpdateRequest memberUpdateRequest = new MemberUpdateRequest("랄프", false);

        //when
        ExtractableResponse<Response> response = updateMemberInfo(bearerToken, loginMember.getId(), memberUpdateRequest);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("로그인이 되어 있지 않은 상태에서 내 정보를 수정할 수 없다.")
    void updateMemberInfoWithoutLogin() {
        //given
        ExtractableResponse<Response> registerResponse = 회원가입("test@gmail.com", "test");
        String token = extractedToken(registerResponse);
        String bearerToken = "Bearer " + token;

        LoginMember loginMember = authService.findMemberByToken(token);

        MemberUpdateRequest memberUpdateRequest = new MemberUpdateRequest("랄프", false);

        //when
        ExtractableResponse<Response> response = updateMemberInfo(null, loginMember.getId(), memberUpdateRequest);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    public ExtractableResponse<Response> updateMemberInfo(String token, Long memberId, MemberUpdateRequest memberUpdateRequest) {
        RequestSpecification requestSpec = RestAssured.given(getRequestSpecification())
                                                      .log().all();
        if (!Objects.isNull(token)) {
            requestSpec.header("Authorization", token);
        }
        return requestSpec.contentType(APPLICATION_JSON_VALUE)
                          .body(memberUpdateRequest)
                          .when()
                          .patch("/api/members/" + memberId)
                          .then()
                          .log().all()
                          .extract();
    }
}