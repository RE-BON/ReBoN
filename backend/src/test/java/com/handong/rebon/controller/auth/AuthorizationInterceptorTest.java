package com.handong.rebon.controller.auth;

import java.util.ArrayList;
import java.util.List;

import com.handong.rebon.auth.application.dto.response.LoginResponseDto;
import com.handong.rebon.auth.domain.LoginMember;
import com.handong.rebon.common.factory.ImageFactory;
import com.handong.rebon.controller.ControllerTest;
import com.handong.rebon.exception.authorization.InvalidTokenException;
import com.handong.rebon.member.application.dto.request.MemberCreateRequestDto;
import com.handong.rebon.member.application.dto.response.MemberCreateResponseDto;
import com.handong.rebon.member.presentation.dto.request.MemberCreateRequest;
import com.handong.rebon.review.application.dto.request.ReviewCreateRequestDto;
import com.handong.rebon.shop.application.dto.request.ShopRequestDto;
import com.handong.rebon.shop.application.dto.request.menu.MenuGroupRequestDto;
import com.handong.rebon.shop.domain.Shop;

import org.springframework.test.web.reactive.server.WebTestClient;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

public class AuthorizationInterceptorTest extends ControllerTest {

    private static final String BEARER = "Bearer ";
    private static final String VALID_ACCESS_TOKEN = "valid.access.token";
    private static final String INVALID_ACCESS_TOKEN = "invalid.access.token";

    @Test
    @DisplayName("GET /auth/{oauthProvider}/login/token - 토큰 검증하지 않음")
    void login() {
        //given
        LoginResponseDto loginResponseDto = new LoginResponseDto("access.token.good");
        given(authService.login(any())).willReturn(loginResponseDto);
        //when
        WebTestClient.ResponseSpec response = webTestClient.get()
                                                           .uri("/api/auth/google/login/token?code=authroizationCode")
                                                           .exchange();
        //then
        response.expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("POST /members - 토큰 검증하지 않음.")
    void signUp() {
        //given
        MemberCreateRequest memberCreateRequest = new MemberCreateRequest("test@email.com", "testnickname", "google", true);
        //when
        WebTestClient.ResponseSpec response = webTestClient.post()
                                                           .uri("/api/members")
                                                           .body(Mono.just(memberCreateRequest), MemberCreateRequest.class)
                                                           .exchange();
        //then
        response.expectStatus()
                .isCreated();
    }

    @Test
    @DisplayName("GET /api/shops/{shopId}/reviews - 적절하지 않은 토큰")
    void getReviewsByShop() {
        //given
        doThrow(new InvalidTokenException()).when(jwtProvider).validateToken(INVALID_ACCESS_TOKEN);

        //when
        WebTestClient.ResponseSpec response = webTestClient.get()
                                                           .uri("/api/shops/1/reviews")
                                                           .header("Authorization", BEARER + INVALID_ACCESS_TOKEN)
                                                           .exchange();

        //then
        response.expectStatus().isUnauthorized();
    }

    @Test
    @DisplayName("GET /api/shops/{shopId}/reviews - 적절한 토큰")
    void getReviewsByShopWithToken() {
        //given
        doNothing().when(jwtProvider).validateToken(VALID_ACCESS_TOKEN);
        Long memberId = saveMember();
        given(authService.findMemberByToken(any())).willReturn(new LoginMember(memberId));
        Long categoryId = categoryService.create("식당");
        Long subCategoryId = categoryService.create("베트남식");
        Long tagId = tagService.createTag("포항");

        ShopRequestDto shopRequestDto = ShopRequestDto.builder()
                                                      .categoryId(categoryId)
                                                      .subCategories(List.of(subCategoryId))
                                                      .tags(List.of(tagId))
                                                      .images(List.of(ImageFactory.createFakeImage("정면사진")))
                                                      .menus(List.of(new MenuGroupRequestDto("피자메뉴", List.of())))
                                                      .name("팜스발리")
                                                      .build();
        Long shopId = shopService.create(shopRequestDto);
        Shop shop = shopService.findById(shopId);
        saveReview(memberId, shop);

        //when
        WebTestClient.ResponseSpec response = webTestClient.get()
                                                           .uri("/api/shops/" + shop.getId() + "/reviews")
                                                           .header("Authorization", BEARER + VALID_ACCESS_TOKEN)
                                                           .exchange();
        //then
        response.expectStatus().isOk();
    }

    private void saveReview(Long memberId, Shop shop) {
        ReviewCreateRequestDto reviewCreateRequestDto = new ReviewCreateRequestDto(memberId, shop.getId(), "테스트 컨텐트", "테스트 팁", new ArrayList<>(), 4);
        reviewService.create(reviewCreateRequestDto);
    }

    private Long saveMember() {
        MemberCreateRequestDto memberCreateRequestDto = new MemberCreateRequestDto("test@email.com", "testnickname", "google", true);
        MemberCreateResponseDto member = memberService.save(memberCreateRequestDto);
        Long memberId = member.getMemberId();
        return memberId;
    }

}
