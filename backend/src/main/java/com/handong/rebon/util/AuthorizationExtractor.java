package com.handong.rebon.util;

import java.util.Enumeration;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

import com.handong.rebon.exception.authorization.AuthorizationHeaderException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorizationExtractor {
    public static final String AUTHENTICATION_TYPE = "Bearer";
    public static final String AUTHORIZATION = "Authorization";

    public static String extractAccessToken(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaders(AUTHORIZATION);
        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if (value.toLowerCase(Locale.ROOT).startsWith(AUTHENTICATION_TYPE.toLowerCase(Locale.ROOT))) {
                return value.split(" ")[1];
            }
        }
        return "";
    }
}
