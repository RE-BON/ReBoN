package com.handong.rebon.unit.oauth;

import com.handong.rebon.auth.domain.OauthProvider;

import org.springframework.http.MediaType;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.apache.http.HttpHeaders;
import org.jetbrains.annotations.NotNull;

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
