package com.handong.rebon.acceptance.member;

import java.util.Objects;

import com.handong.rebon.acceptance.AcceptanceTest;
import com.handong.rebon.auth.application.AuthService;
import com.handong.rebon.member.application.MemberService;
import com.handong.rebon.member.presentation.dto.request.MemberUpdateRequest;
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

public class MemberUpdateAcceptanceTest extends AcceptanceTest {
    @Autowired
    private AuthService authService;

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("로그인인 상태에서는 내 정보를 수정할 수 있다.")
    void updateMemberInfoWithLogin() {
        //given
        ExtractableResponse<Response> registerResponse = 회원가입("test@gmail.com", "test");
        String token = extractedToken(registerResponse);
        String bearerToken = "Bearer " + token;
        String nickName = "랄프";

        MemberUpdateRequest memberUpdateRequest = new MemberUpdateRequest(nickName, false);

        //when
        ExtractableResponse<Response> response = updateMemberInfo(bearerToken, memberUpdateRequest);
        ExtractableResponse<Response> updatedResponse = getMemberInfo(bearerToken);
        MemberReadResponse result = updatedResponse.as(MemberReadResponse.class);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(result).extracting("email")
                          .isEqualTo("test@gmail.com");
        assertThat(result).extracting("nickName")
                          .isEqualTo("랄프");
        assertThat(result).extracting("agreed")
                          .isEqualTo(false);
    }

    @Test
    @DisplayName("로그인이 되어 있지 않은 상태에서 내 정보를 수정할 수 없다.")
    void updateMemberInfoWithoutLogin() {
        //given
        ExtractableResponse<Response> registerResponse = 회원가입("test@gmail.com", "test");
        String token = extractedToken(registerResponse);
        String bearerToken = "Bearer " + token;
        String nickName = "랄프";

        memberService.checkNicknameDuplicate(nickName);
        MemberUpdateRequest memberUpdateRequest = new MemberUpdateRequest(nickName, false);

        //when
        ExtractableResponse<Response> response = updateMemberInfo(null, memberUpdateRequest);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    public ExtractableResponse<Response> updateMemberInfo(String token, MemberUpdateRequest memberUpdateRequest) {
        RequestSpecification requestSpec = RestAssured.given(getRequestSpecification())
                                                      .log().all();
        if (!Objects.isNull(token)) {
            requestSpec.header("Authorization", token);
        }
        return requestSpec.contentType(APPLICATION_JSON_VALUE)
                          .body(memberUpdateRequest)
                          .when()
                          .patch("/api/members")
                          .then()
                          .log().all()
                          .extract();
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