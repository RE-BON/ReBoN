package com.handong.rebon.unit.auth;

import com.handong.rebon.auth.infrastructure.JwtProvider;
import com.handong.rebon.exception.authorization.InvalidTokenException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class JwtProviderTest {

    @Test
    @DisplayName("jwt를 셍성한다.")
    void createJwt() {
        //given
        JwtProvider jwtProvider = new JwtProvider("helloWorld", 8640000L);

        //when
        String token = jwtProvider.createToken("1");

        //then
        assertThat(token).isNotNull();
    }

    @Test
    @DisplayName("jwt payload를 얻을 수 있다.")
    void getPayLoadByJwt() {
        //given
        JwtProvider jwtProvider = new JwtProvider("helloWorld", 8640000L);
        Long memberId = 1L;
        String token = jwtProvider.createToken(String.valueOf(memberId));

        //when
        String payLoad = jwtProvider.getPayLoad(token);

        //then
        assertThat(payLoad).isEqualTo(memberId.toString());
    }

    @Test
    @DisplayName("올바르지 않은 토큰이 오면, InvalidTokenException이 일어난다.")
    void getInvalidToken() {
        //given
        JwtProvider jwtProvider = new JwtProvider("helloWorld", 8640000L);
        //when,then
        assertThatThrownBy(() -> jwtProvider.validateToken("invalidToken"))
                .isInstanceOf(InvalidTokenException.class);
    }
}
