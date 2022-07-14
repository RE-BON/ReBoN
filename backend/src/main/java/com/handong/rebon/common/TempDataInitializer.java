package com.handong.rebon.common;

import java.util.*;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.category.domain.repository.CategoryRepository;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImage;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.repository.ShopRepository;
import com.handong.rebon.shop.domain.type.Cafe;
import com.handong.rebon.shop.domain.type.Lodging;
import com.handong.rebon.shop.domain.type.Restaurant;
import com.handong.rebon.tag.domain.Tag;
import com.handong.rebon.tag.domain.repository.TagRepository;
import com.handong.rebon.tag.domain.repository.TagSearchRepository;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class TempDataInitializer implements ApplicationRunner {
    private final TagRepository tagRepository;
    private final TagSearchRepository tagSearchRepository;
    private final CategoryRepository categoryRepository;
    private final ShopRepository shopRepository;

    private Map<String, Tag> tags = new HashMap<>();
    private Map<String, Category> categories = new HashMap<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initTags();
        initCategories();
        initShops();
    }

    private void initTags() {
        Tag 포항 = new Tag("포항");
        Tag 한동대 = new Tag("한동대");
        Tag 양덕 = new Tag("양덕");
        Tag 영일대 = new Tag("영일대");

        this.tags.put(포항.getName(), 포항);
        this.tags.put(한동대.getName(), 한동대);
        this.tags.put(양덕.getName(), 양덕);
        this.tags.put(영일대.getName(), 영일대);

        tagRepository.saveAll(tags.values());
        tagSearchRepository.saveAll(tags.values());
    }

    private void initCategories() {
        Category 식당 = new Category("식당");
        this.categories.put(식당.getName(), 식당);
        this.categories.put("한식", new Category("한식", 식당));
        this.categories.put("중식", new Category("중식", 식당));
        this.categories.put("일식", new Category("일식", 식당));
        this.categories.put("양식", new Category("양식", 식당));
        this.categories.put("분식", new Category("분식", 식당));
        this.categories.put("치킨", new Category("치킨", 식당));

        Category 카페 = new Category("카페");
        this.categories.put(카페.getName(), 카페);
        this.categories.put("프랜차이즈", new Category("프랜차이즈", 카페));
        this.categories.put("개인 카페", new Category("개인 카페", 카페));

        Category 숙소 = new Category("숙소");
        this.categories.put(숙소.getName(), 숙소);
        this.categories.put("호텔", new Category("호텔", 숙소));
        this.categories.put("모텔", new Category("모텔", 숙소));
        this.categories.put("게스트하우스", new Category("게스트하우스", 숙소));
        this.categories.put("풀빌라", new Category("풀빌라", 숙소));

        categoryRepository.saveAll(categories.values());
    }

    private void initShops() {
        createRestaurant("팜스발리", 4.9, Arrays.asList(tags.get("포항"), tags.get("한동대")),
                Arrays.asList(categories.get("양식"), categories.get("치킨")));
        createRestaurant("인브리즈", 4.8, Arrays.asList(tags.get("포항"), tags.get("양덕")),
                Collections.singletonList(categories.get("양식")));
        createRestaurant("인브리즈", 4.9, Arrays.asList(tags.get("포항"), tags.get("한동대")),
                Collections.singletonList(categories.get("양식")));
        createRestaurant("쿠킹빌리지", 4.8, Arrays.asList(tags.get("포항"), tags.get("양덕")),
                Collections.singletonList(categories.get("양식")));
        createRestaurant("파스타입니다", 4.9, Arrays.asList(tags.get("포항"), tags.get("양덕")),
                Collections.singletonList(categories.get("양식")));
        createRestaurant("스시요시", 4.7, Arrays.asList(tags.get("포항"), tags.get("양덕")),
                Collections.singletonList(categories.get("일식")));
        createRestaurant("우공 초밥", 4.6, Arrays.asList(tags.get("포항"), tags.get("양덕")),
                Collections.singletonList(categories.get("일식")));
        createRestaurant("바른치킨", 4.9, Arrays.asList(tags.get("포항"), tags.get("양덕")),
                Arrays.asList(categories.get("치킨"), categories.get("분식")));
        createRestaurant("치킨을 사랑한 떡볶이", 4.7, Arrays.asList(tags.get("포항"), tags.get("양덕")),
                Arrays.asList(categories.get("치킨"), categories.get("분식")));
        createRestaurant("청년 다방", 4.8, Arrays.asList(tags.get("포항"), tags.get("양덕")),
                Arrays.asList(categories.get("치킨"), categories.get("분식")));

    }

    private void createRestaurant(String name, double star, List<Tag> tags, List<Category> subs) {
        Restaurant restaurant = Restaurant.builder()
                                          .shopContent(new ShopContent(name))
                                          .shopImages(new ShopImages(
                                                  Collections.singletonList(
                                                          new ShopImage("https://previews.123rf.com/images/julynx/julynx1408/julynx140800023/30746516-%EC%82%AC%EC%9A%A9%ED%95%A0-%EC%88%98-%EC%97%86%EA%B1%B0%EB%82%98-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%82%AC%EC%A7%84-%EC%97%86%EC%9D%8C.jpg", true)
                                                  )
                                          ))
                                          .address("포항")
                                          .shopScore(new ShopScore(star))
                                          .build();
        restaurant.addMenu(Collections.emptyList());
        restaurant.addTags(tags);
        restaurant.addCategories(categories.get("식당"), subs);
        shopRepository.save(restaurant);
    }

    private void createCafe(String name, double star, List<Tag> tags, List<Category> subs) {
        Cafe cafe = Cafe.builder()
                        .shopContent(new ShopContent(name))
                        .shopImages(new ShopImages(
                                Collections.singletonList(
                                        new ShopImage("https://previews.123rf.com/images/julynx/julynx1408/julynx140800023/30746516-%EC%82%AC%EC%9A%A9%ED%95%A0-%EC%88%98-%EC%97%86%EA%B1%B0%EB%82%98-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%82%AC%EC%A7%84-%EC%97%86%EC%9D%8C.jpg", true)
                                )
                        ))
                        .shopScore(new ShopScore(star))
                        .build();
        cafe.addMenu(Collections.emptyList());
        cafe.addTags(tags);
        cafe.addCategories(categories.get("카페"), subs);
        shopRepository.save(cafe);
    }

    private void createLodging(String name, double star, List<Tag> tags, List<Category> subs) {
        Lodging lodging = Lodging.builder()
                                 .shopContent(new ShopContent(name))
                                 .shopImages(new ShopImages(
                                         Collections.singletonList(
                                                 new ShopImage("https://previews.123rf.com/images/julynx/julynx1408/julynx140800023/30746516-%EC%82%AC%EC%9A%A9%ED%95%A0-%EC%88%98-%EC%97%86%EA%B1%B0%EB%82%98-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%82%AC%EC%A7%84-%EC%97%86%EC%9D%8C.jpg", true)
                                         )
                                 ))
                                 .shopScore(new ShopScore(star))
                                 .build();
        lodging.addTags(tags);
        lodging.addCategories(categories.get("숙소"), subs);
        shopRepository.save(lodging);
    }
}
