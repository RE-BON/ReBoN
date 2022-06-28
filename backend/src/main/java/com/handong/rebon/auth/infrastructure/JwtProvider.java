package com.handong.rebon.auth.infrastructure;

import java.util.Date;

import com.handong.rebon.exception.authorization.InvalidTokenException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

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

    public String createToken(String payload) {
        Date now = new Date();
        Date expiredDay = new Date(now.getTime() + expiredTime);
        Claims claims = Jwts.claims().setSubject(payload);

        return Jwts.builder()
                   .setIssuedAt(now)
                   .setIssuer("ReBoN")
                   .setExpiration(expiredDay)
                   .setClaims(claims)
                   .signWith(SignatureAlgorithm.HS256, secretKey)
                   .compact();

    }

    public void validateToken(String token) {
        try {
            JwtParser jwtParser = getJwtParser();
            jwtParser.parseClaimsJws(token);
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidTokenException();
        }
    }

    public String getPayLoad(String token) {
        JwtParser jwtParser = getJwtParser();
        return jwtParser.parseClaimsJws(token)
                        .getBody()
                        .getSubject();
    }

    private JwtParser getJwtParser() {
        return Jwts.parser()
                   .setSigningKey(secretKey);
    }

    public boolean isValidToken(String token) {
        try {
            validateToken(token);
            return true;
        } catch (InvalidTokenException e) {
            return false;
        }
    }
}
