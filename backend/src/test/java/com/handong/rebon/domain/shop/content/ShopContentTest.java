package com.handong.rebon.domain.shop.content;

import com.handong.rebon.exception.shop.ShopNameException;
import com.handong.rebon.shop.domain.content.ShopContent;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ShopContentTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("이름이 비어있거나 공백으로만 들어온 경우 등록할 수 없다.")
    void blankName(String name) {
        // given
        // when & then
        assertThatThrownBy(() -> ShopContent.builder()
                                            .name(name)
                                            .build())
                .isInstanceOf(ShopNameException.class);
    }
}
