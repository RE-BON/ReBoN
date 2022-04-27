package com.handong.rebon.auth.infrastructure;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import com.handong.rebon.exception.oauth.GetAccessTokenException;
import com.handong.rebon.exception.oauth.UnableToGetTokenResponseException;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiRequester {

    public static Map<String, Object> getUserInfo(
            String code,
            String tokenUri,
            String clientId,
            String secretId,
            String redirectUri,
            String userInfoUri
    ) {
        String token = getToken(code, tokenUri, clientId, secretId, redirectUri);
        return getUserInfoByToken(token, userInfoUri);
    }

    private static String getToken(String code, String tokenUri, String clientId, String secretId, String redirectUri) {
        Map<String, Object> responseBody = WebClient.create()
                                                    .post()
                                                    .uri(tokenUri)
                                                    .headers(header -> {
                                                        header.setBasicAuth(clientId, secretId);
                                                        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                                                        header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                                                        header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                                                    })
                                                    .bodyValue(tokenRequest(code, redirectUri))
                                                    .retrieve()
                                                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                                                    })
                                                    .flux()
                                                    .toStream()
                                                    .findFirst()
                                                    .orElseThrow(UnableToGetTokenResponseException::new);
        validateResponseBody(responseBody);
        return responseBody.get("access_token").toString();
    }

    private static MultiValueMap<String, String> tokenRequest(String code, String redirectUri) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", redirectUri);
        return formData;
    }

    private static void validateResponseBody(Map<String, Object> responseBody) {
        if (!responseBody.containsKey("access_token")) {
            throw new GetAccessTokenException();
        }
    }

    private static Map<String, Object> getUserInfoByToken(String token, String userInfoUri) {
        Map<String, Object> responseBody = WebClient.create()
                                                    .get()
                                                    .uri(userInfoUri)
                                                    .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
                                                    .retrieve()
                                                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                                                    })
                                                    .flux()
                                                    .toStream()
                                                    .findFirst()
                                                    .orElseThrow(UnableToGetTokenResponseException::new);

        return responseBody;
    }
}
