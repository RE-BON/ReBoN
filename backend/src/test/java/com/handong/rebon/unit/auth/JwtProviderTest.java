package com.handong.rebon.unit.auth;

import com.handong.rebon.auth.infrastructure.JwtProvider;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JwtProviderTest {

    @Test
    @DisplayName("jwt를 셍성한다.")
    void createJwt() {
        //given
        JwtProvider jwtProvider = new JwtProvider("helloWorld", 8640000L);

        //when
        String token = jwtProvider.createToken(1L);

        //then
        assertThat(token).isNotNull();
    }
}
