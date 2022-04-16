package com.handong.rebon.unit.auth;

import com.handong.rebon.auth.infrastructure.JwtUtils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JwtUtilsTest {

    @Test
    @DisplayName("jwt를 셍성한다.")
    void createJwt() {
        //given
        JwtUtils jwtUtils = new JwtUtils("helloWorld", 8640000L);

        //when
        String token = jwtUtils.createToken(1L);

        //then
        assertThat(token).isNotNull();
    }
}
