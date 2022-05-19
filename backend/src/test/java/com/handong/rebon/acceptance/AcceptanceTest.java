package com.handong.rebon.acceptance;

import com.handong.rebon.acceptance.member.MemberCreateAcceptanceTest;
import com.handong.rebon.config.InfrastructureTestConfig;
import com.handong.rebon.member.presentation.dto.request.MemberCreateRequest;
import com.handong.rebon.member.presentation.dto.response.MemberCreateResponse;
import com.handong.rebon.util.DatabaseCleaner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.handong.rebon.acceptance.AcceptanceUtils.setRequestSpecification;
import static com.handong.rebon.acceptance.member.MemberCreateAcceptanceTest.saveMember;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

@Import(InfrastructureTestConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class AcceptanceTest {

    public static final String TEST_EMAIL = "handong@gmail.com";
    public static final String TEST_OAUTH_PROVIDER = "google";
    public static final String TEST_CODE = "test-code";
    public static final String TEST_NICKNAME = "test";
    public String token;

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner cleaner;

    @BeforeEach
    void init(RestDocumentationContextProvider restDocumentation) {
        RestAssured.port = port;
        RequestSpecification spec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation)
                        .operationPreprocessors()
                        .withRequestDefaults(prettyPrint())
                        .withResponseDefaults(prettyPrint()))
                .addFilter(document("{ClassName}/{methodName}"))
                .build();
        setRequestSpecification(spec);
        cleaner.execute();
        ExtractableResponse<Response> memberResponse = saveMember();
        token = extractedToken(memberResponse);
    }

    private String extractedToken(ExtractableResponse<Response> memberResponse) {
        MemberCreateResponse memberCreateResponse = memberResponse.body().as(MemberCreateResponse.class);
        return memberCreateResponse.getToken();
    }

    private ExtractableResponse<Response> saveMember() {
        MemberCreateRequest memberCreateRequest = new MemberCreateRequest(
                "test@gmail.com",
                "test",
                TEST_OAUTH_PROVIDER,
                true
        );
        ExtractableResponse<Response> memberResponse = MemberCreateAcceptanceTest.saveMember(memberCreateRequest);
        return memberResponse;
    }

    public String getToken() {
        return token;
    }
}
