package com.handong.rebon.shop.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.exception.OpenApiServerException;
import com.handong.rebon.shop.infrastructure.dto.NaverShopDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class NaverShopInserter {
    private static final int TOTAL_COUNT = 300; // api 호출시 최대 300개 까지 검색가능한듯

    private final WebClient webClient;
    private final RestTemplate restTemplate;

    public NaverShopInserter(WebClient webClient, RestTemplate restTemplate) {
        this.webClient = webClient;
        this.restTemplate = restTemplate;
    }

    public List<NaverShopDto> getShops(Category category, String query, String condition) {
        List<NaverShopDto> results = new ArrayList<>();
        int displayCount = 10;

        NaverShopDto shopDto = getShop(query, condition, 1, displayCount);
        results.add(shopDto);

        int pageCount = (int) Math.ceil((double) shopDto.getTotalCount() / displayCount);
        if (pageCount > TOTAL_COUNT / displayCount) {
            pageCount = 31;
        }

        for (int page = 2; page <= pageCount; page++) {
            NaverShopDto result = getShop(query, condition, page, displayCount);
            results.add(result);
        }

        results.forEach(dto -> dto.setBasicData(category, query));

        return new ArrayList<>(results);
    }

    private NaverShopDto getShop(String query, String condition, int page, int displayCount) {
        String url = String.format("https://map.naver.com/v5/api/search?caller=pcweb&query=%s %s&type=all&displayCount=%d&lang=ko&page=%d", query, condition, displayCount, page);
        ResponseEntity<NaverShopDto> response = restTemplate.getForEntity(url, NaverShopDto.class);
        return response.getBody();
    }

    /**
     * 503 에러 발생.. 저번에 한 번 데이터 받아오는거 성공했는데
     * 다시해보니 에러 발생..
     */
    public List<NaverShopDto> getShopsWebClient(Category category, String query, String condition) {
        try {
            List<NaverShopDto> results = new ArrayList<>();
            int displayCount = 10;
            int pageCount = TOTAL_COUNT / displayCount;

            CountDownLatch latch = new CountDownLatch(pageCount);

            for (int page = 1; page <= pageCount; page++) {
                getShopsWebClient(results, latch, query, condition, page, displayCount);
            }

            latch.await();

            results.forEach(dto -> dto.setBasicData(category, query));

            return new ArrayList<>(results);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new OpenApiServerException();
        }
    }

    private void getShopsWebClient(List<NaverShopDto> results, CountDownLatch latch, String query, String condition, int page, int displayCount) {
        sendRequest(query, condition, page, displayCount)
                .bodyToMono(NaverShopDto.class)
                .subscribe(result -> {
                    results.add(result);
                    latch.countDown();
                });
    }

    private WebClient.ResponseSpec sendRequest(String query, String condition, int page, int displayCount) {
        return webClient.get()
                        .uri("https://map.naver.com", uri -> uri
                                .path("/v5/api/search")
                                .queryParam("caller", "pcweb")
                                .queryParam("query", query)
                                .queryParam("type", "all")
                                .queryParam("displayCount", displayCount)
                                .queryParam("lang", "ko")
                                .queryParam("page", page).build())
                        .retrieve()
                        .onStatus(HttpStatus::isError, error -> Mono.error(OpenApiServerException::new));
    }
}
