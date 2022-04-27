package com.handong.rebon.auth.infrastructure;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Component
public class JwtProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expired-time}")
    private Long expiredTime;

    public String createToken(Long memberId) {
        Date now = new Date();
        Date expiredDay = new Date(now.getTime() + expiredTime);

        return Jwts.builder()
                   .setIssuedAt(now)
                   .setIssuer("ReBoN")
                   .setExpiration(expiredDay)
                   .claim("id", memberId)
                   .signWith(SignatureAlgorithm.HS256, secretKey)
                   .compact();

    }
}
