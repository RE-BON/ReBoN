package com.handong.rebon.unit.oauth;

import java.io.IOException;

import com.handong.rebon.auth.domain.OauthAttributes;
import com.handong.rebon.auth.domain.OauthProvider;
import com.handong.rebon.auth.domain.OauthUserInfo;
import com.handong.rebon.auth.infrastructure.ApiRequester;
import com.handong.rebon.exception.oauth.GetAccessTokenException;

import org.springframework.http.MediaType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.apache.http.HttpHeaders;
import org.jetbrains.annotations.NotNull;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ApiRequesterTest {

    public static final String TEST_EMAIL = "handong@gmail.com";

    @NotNull
    protected OauthProvider makeOauthProvider(MockWebServer mockWebServer) {
        return new OauthProvider(
                "clientId",
                "secretId",
                "redirectUri",
                String.format("http://%s:%s", mockWebServer.getHostName(), mockWebServer.getPort()),
                String.format("http://%s:%s", mockWebServer.getHostName(), mockWebServer.getPort())
        );
    }

    protected void setUpResponse(MockWebServer mockWebServer, String body) {
        mockWebServer.enqueue(new MockResponse()
                .setBody(body)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
    }
}
