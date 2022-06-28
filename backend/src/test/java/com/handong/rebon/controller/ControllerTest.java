package com.handong.rebon.controller;

import com.handong.rebon.auth.application.AuthService;
import com.handong.rebon.auth.infrastructure.JwtProvider;
import com.handong.rebon.category.application.CategoryService;
import com.handong.rebon.common.admin.AdminShopRegister;
import com.handong.rebon.config.InfrastructureTestConfig;
import com.handong.rebon.config.NotUseElasticSearchConfig;
import com.handong.rebon.member.application.MemberService;
import com.handong.rebon.review.application.ReviewService;
import com.handong.rebon.shop.application.ShopService;
import com.handong.rebon.tag.application.TagService;
import com.handong.rebon.tag.domain.repository.TagSearchRepository;
import com.handong.rebon.util.DatabaseCleaner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import org.junit.jupiter.api.BeforeEach;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import({InfrastructureTestConfig.class, NotUseElasticSearchConfig.class})
public class ControllerTest {

    @Autowired
    private DatabaseCleaner cleaner;

    @MockBean
    protected AuthService authService;

    @MockBean
    protected JwtProvider jwtProvider;

    @Autowired
    protected MemberService memberService;

    @Autowired
    protected WebTestClient webTestClient;

    @Autowired
    protected CategoryService categoryService;

    @Autowired
    protected TagService tagService;

    @Autowired
    protected ShopService shopService;

    @Autowired
    protected AdminShopRegister adminShopRegister;

    @Autowired
    protected ReviewService reviewService;

    @MockBean
    private ElasticsearchTemplate elasticsearchTemplate;

    @MockBean
    private TagSearchRepository tagSearchRepository;

    @BeforeEach
    void init() {
        cleaner.execute();
    }
}
