package com.handong.rebon.auth.infrastructure;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import com.handong.rebon.auth.domain.GoogleUserInfo;
import com.handong.rebon.auth.domain.OauthProvider;
import com.handong.rebon.auth.domain.OauthUserInfo;
import com.handong.rebon.exception.oauth.ErrorResponseToGetAccessTokenException;
import com.handong.rebon.exception.oauth.UnableToGetTokenResponseFromGoogleException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Component
public class GoogleRequester implements OauthAPIRequester {

    @Value("${oauth2.user.google.client-id}")
    private String clientId;

    @Value("${oauth2.user.google.client-secret}")
    private String secretId;

    @Value("${oauth2.user.google.redirect-uri}")
    private String redirectUri;

    @Value("${oauth2.provider.google.token-uri}")
    private String tokenUri;

    @Value("${oauth2.provider.google.user-info-uri}")
    private String userInfoUri;

    @Override
    public boolean supports(OauthProvider oauthProvider) {
        return oauthProvider.isSameAs(OauthProvider.GOOGLE);
    }

    @Override
    public OauthUserInfo getUserInfoByCode(final String code) {
        String token = getToken(code);
        return getUserInfo(token);
    }


    private String getToken(final String code) {
        Map<String, Object> responseBody = WebClient.create()
                                                    .post()
                                                    .uri(tokenUri)
                                                    .headers(header -> {
                                                        header.setBasicAuth(clientId, secretId);
                                                        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                                                        header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                                                        header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
                                                    })
                                                    .bodyValue(tokenRequest(code))
                                                    .retrieve()
                                                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                                                    })
                                                    .blockOptional()
                                                    .orElseThrow(UnableToGetTokenResponseFromGoogleException::new);
        validateResponseBody(responseBody);
        return responseBody.get("access_token").toString();
    }

    private MultiValueMap<String, String> tokenRequest(String code) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", redirectUri);
        return formData;
    }

    private void validateResponseBody(Map<String, Object> responseBody) {
        if (!responseBody.containsKey("access_token")) {
            throw new ErrorResponseToGetAccessTokenException();
        }
    }

    private GoogleUserInfo getUserInfo(final String token) {
        Map<String, Object> responseBody = WebClient.create()
                                                    .get()
                                                    .uri(userInfoUri)
                                                    .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
                                                    .retrieve()
                                                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                                                    })
                                                    .blockOptional()
                                                    .orElseThrow(UnableToGetTokenResponseFromGoogleException::new);

        return GoogleUserInfo.from(responseBody);
    }

}
