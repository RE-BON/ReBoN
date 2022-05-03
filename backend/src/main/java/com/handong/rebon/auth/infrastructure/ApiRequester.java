package com.handong.rebon.auth.infrastructure;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import com.handong.rebon.auth.domain.OauthProvider;
import com.handong.rebon.exception.oauth.GetAccessTokenException;
import com.handong.rebon.exception.oauth.UnableToGetTokenResponseException;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

public class ApiRequester {

    public Map<String, Object> getUserInfo(String code, OauthProvider oauthProvider) {
        String token = getToken(code, oauthProvider);
        return getUserInfoByToken(token, oauthProvider.getUserInfoUrl());
    }

    private String getToken(String code, OauthProvider oauthProvider) {
        Map<String, Object> responseBody = WebClient.create()
                                                    .post()
                                                    .uri(oauthProvider.getTokenUrl())
                                                    .headers(header -> {
                                                        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                                                        header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                                                        header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                                                    })
                                                    .bodyValue(tokenRequest(code, oauthProvider))
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

    private MultiValueMap<String, String> tokenRequest(String code, OauthProvider oauthProvider) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", oauthProvider.getRedirectUrl());
        formData.add("client_id", oauthProvider.getClientId());
        formData.add("client_secret", oauthProvider.getClientSecret());
        return formData;
    }

    private void validateResponseBody(Map<String, Object> responseBody) {
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
