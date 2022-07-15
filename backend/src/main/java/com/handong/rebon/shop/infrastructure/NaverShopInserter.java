package com.handong.rebon.shop.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.handong.rebon.exception.OpenApiServerException;
import com.handong.rebon.shop.infrastructure.dto.NaverShopDto;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class NaverShopInserter {
    private static final int TOTAL_COUNT = 300; // api 호출시 최대 300개 까지 검색가능한듯

    private final WebClient webClient;

    public NaverShopInserter(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<NaverShopDto> getShops(String query) {
        try {
            int displayCount = 10;
            int pageCount = TOTAL_COUNT / displayCount;

            CountDownLatch latch = new CountDownLatch(pageCount);
            List<NaverShopDto> results = new ArrayList<>();

            for (int page = 1; page <= pageCount; page++) {
                getShops(results, latch, query, page, displayCount);
            }

            latch.await();
            return new ArrayList<>(results);
        } catch (InterruptedException e) {
            throw new IllegalArgumentException();
        }
    }

    private void getShops(List<NaverShopDto> results, CountDownLatch latch, String query, int page, int displayCount) {
        sendRequest(query, page, displayCount)
                .bodyToMono(NaverShopDto.class)
                .subscribe(result -> {
                    results.add(result);
                    latch.countDown();
                }, error -> {
                    throw new OpenApiServerException();
                });
    }

    private WebClient.ResponseSpec sendRequest(String query, int page, int displayCount) {
        return webClient.get()
                        .uri("https://map.naver.com/", uri -> uri
                                .path("/v5/api/search")
                                .queryParam("caller", "pcweb")
                                .queryParam("query", query)
                                .queryParam("type", "all")
                                .queryParam("displayCount", displayCount)
                                .queryParam("lang", "ko")
                                .queryParam("page", page).build())
                        .retrieve();
    }
}
