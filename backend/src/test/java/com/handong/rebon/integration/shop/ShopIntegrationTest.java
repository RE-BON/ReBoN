package com.handong.rebon.integration.shop;

import java.util.Arrays;

import com.handong.rebon.shop.application.ShopService;
import com.handong.rebon.shop.application.dto.ShopRequestDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ShopIntegrationTest {

    @Autowired
    private ShopService shopService;

    @Test
    @DisplayName("단일 가게 생성")
    void createOne() {
        // given
        // TODO 나중에 로그인 된 유저만 할 수 있는지도 검증해야함
        ShopRequestDto shopRequestDto = ShopRequestDto.builder()
                                                      .categoryId(1L) // TODO 카테고리 파트와 머지 되면 여러 값 넣어보면서 테스트
                                                      .name("팜스발리")
                                                      .businessHour("10:00 ~ 22:00")
                                                      .phone("010-1234-5678")
                                                      .address("경상북도 포항")
                                                      .longitude("129.389762")
                                                      .latitude("36.102440")
                                                      .tags(Arrays.asList(1L, 2L, 3L)) // TODO 태그 파트와 머지 되면 여러 값 넣어보면서 테스트
                                                      //.images() TODO 이미지 저장 구현되면 추가
                                                      //.menus() TODO 메뉴 저장 구현되면 추가
                                                      .build();

        // when
        Long id = shopService.create(shopRequestDto);

        // then
        assertThat(id).isNotNull();
        // TODO 태그완료 되면 ShopTag에도 값이 저장되는지 확인해봐야함(지금은 엔티티매니저가 관리하고 있지 않아서 쿼리가 안나가는듯)
    }
}
