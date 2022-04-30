package com.handong.rebon.auth.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@ConfigurationProperties(prefix = "oauth2")
public class OauthProperties {

    private final Map<String, User> user = new HashMap<>();
    private final Map<String, Provider> provider = new HashMap<>();

    @Getter
    @Setter
    public static class User {
        private String clientId;
        private String clientSecret;
        private String redirectUri;
    }

    @Getter
    @Setter
    public static class Provider {
        private String tokenUri;
        private String userInfoUri;
    }

    public Map<String, OauthProvider> getOauthProviders() {
        Map<String, OauthProvider> oauthProviders = new HashMap<>();
        user.forEach((key, value) -> oauthProviders.put(key, new OauthProvider(value, provider.get(key))));

        return oauthProviders;
    }
}
