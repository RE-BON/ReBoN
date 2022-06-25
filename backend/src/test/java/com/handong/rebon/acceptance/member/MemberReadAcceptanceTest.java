package com.handong.rebon.acceptance.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.handong.rebon.acceptance.AcceptanceTest;
import com.handong.rebon.common.admin.AdminShopRegister;
import com.handong.rebon.member.application.MemberService;
import com.handong.rebon.member.application.dto.request.MemberCreateRequestDto;
import com.handong.rebon.member.application.dto.response.MemberReadResponseDto;
import com.handong.rebon.member.domain.Member;
import com.handong.rebon.member.presentation.dto.request.MemberCreateRequest;
import com.handong.rebon.review.presentation.dto.request.ReviewRequest;
import com.handong.rebon.review.presentation.dto.response.ReviewGetByShopResponse;
import com.handong.rebon.shop.domain.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.BeforeEach;
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

public class MemberReadAcceptanceTest extends AcceptanceTest {
    @Autowired
    private MemberService memberService;

    private Map<String, Member> members = new HashMap<>();

    @BeforeEach
    void setUp() {
        String code = "test-code";
        String email = "test@gmail.com";
        String registeredProvider = "google";
        String nickName ="test";
        memberService.save(new MemberCreateRequestDto(email, registeredProvider, nickName, true));
    }

    @Test
    @DisplayName("내 정보를 조회할 수 있다.")
    void findMemberInfo(){
        //given
        Long memberId = members.get("test@gmail.com").getId(); //현재 로그인 된 사람의 아이디로 어떻게 바꿀까

        // when
        ExtractableResponse<Response> response = getMemberInfo(memberId);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public ExtractableResponse<Response> getMemberInfo(Long memberId) {
        return RestAssured.given(getRequestSpecification())
                .contentType(APPLICATION_JSON_VALUE)
                          .when()
                          .get("/api/member/" + memberId)
                          .then()
                          .log().all()
                          .extract();
    }
}