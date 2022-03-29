package com.handong.rebon.unit.shop.domain.repository;

import java.util.*;
import javax.persistence.EntityManager;

import com.handong.rebon.category.Category;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.ShopSearchCondition;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImage;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.repository.ShopRepository;
import com.handong.rebon.shop.domain.type.Cafe;
import com.handong.rebon.shop.domain.type.Restaurant;
import com.handong.rebon.tag.domain.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ShopRepositoryCustomImplTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ShopRepository shopRepository;

    private Tag 포항;
    private Tag 영일대;
    private Tag 한동대;
    private Tag 양덕;

    private Category 식당;
    private Category 카페;
    private Category 숙소;
    private Category 한식;
    private Category 분식;
    private Category 양식;
    private Category 프랜차이즈;
    private Category 개인카페;

    @BeforeEach
    void setUp() {
        initTags();
        initCategory();
        initShops();
    }

    @Test
    @DisplayName("포항에 있는 모든 식당을 가져온다. - 총 6개, 한 페이지 당 2개, 별점 높은 순 정렬")
    void allRestaurantInPohang() {
        // given
        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "shopScore.star"));
        ShopSearchCondition condition = new ShopSearchCondition(포항, 식당);

        // when
        Page<Shop> result = shopRepository.searchShopByConditionApplyPage(condition, pageable);
        List<Shop> content = result.getContent();

        // then
        assertThat(result.getTotalPages()).isEqualTo(3);
        assertThat(content).hasSize(2);
        assertThat(content).extracting("shopContent")
                           .extracting("name")
                           .containsOnly("팜스발리", "바벤");
        assertThat(content.get(0).getShopTags()).hasSize(2);
        assertThat(content.get(0).getShopTags()).extracting("tag")
                                                .extracting("name")
                                                .containsOnly("포항", "한동대");
    }

    @Test
    @DisplayName("포항의 모든 한식 식당을 가져온다. - 총 4개, 한 페이지 당 2개, 별점 높은 순 정렬")
    void allKoreanRestaurantInPohang() {
        // given
        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "shopScore.star"));
        ShopSearchCondition condition = new ShopSearchCondition(포항, 식당, Collections.singletonList(한식));

        // when
        Page<Shop> result = shopRepository.searchShopByConditionApplyPage(condition, pageable);
        List<Shop> content = result.getContent();

        // then
        assertThat(result.getTotalPages()).isEqualTo(2);
        assertThat(content).hasSize(2);
        assertThat(content).extracting("shopContent")
                           .extracting("name")
                           .containsOnly("바벤", "할매국밥");
        assertThat(content.get(0).getShopTags()).hasSize(2);
        assertThat(content.get(0).getShopTags()).extracting("tag")
                                                .extracting("name")
                                                .containsOnly("포항", "양덕");
    }

    @Test
    @DisplayName("양덕에 있는 모든 프랜차이즈나 개인 카페를 가져온다 - 총 2개, 한 페이지 당 2개, 별점 높은 순 정렬")
    void allFranchiseOrPersonalCafeInYangdeok() {
        // given
        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "shopScore.star"));
        ShopSearchCondition condition = new ShopSearchCondition(양덕, 카페, Arrays.asList(프랜차이즈, 개인카페));

        // when
        Page<Shop> result = shopRepository.searchShopByConditionApplyPage(condition, pageable);
        List<Shop> content = result.getContent();

        // then
        assertThat(result.getTotalPages()).isEqualTo(1);
        assertThat(content).hasSize(2);
        assertThat(content).extracting("shopContent")
                           .extracting("name")
                           .containsOnly("커피유야", "이디야");
        assertThat(content.get(0).getShopTags()).hasSize(2);
        assertThat(content.get(0).getShopTags()).extracting("tag")
                                                .extracting("name")
                                                .containsOnly("포항", "양덕");
    }

    @Test
    @DisplayName("한동대에 있는 모든 카페를 가져온다 - 총 1개, 한 페이지 당 2개, 별점 높은 순 정렬")
    void allCafeInHandong() {
        // given
        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "shopScore.star"));
        ShopSearchCondition condition = new ShopSearchCondition(한동대, 카페);

        // when
        Page<Shop> result = shopRepository.searchShopByConditionApplyPage(condition, pageable);
        List<Shop> content = result.getContent();

        // then
        assertThat(result.getTotalPages()).isEqualTo(1);
        assertThat(content).hasSize(1);
        assertThat(content).extracting("shopContent")
                           .extracting("name")
                           .containsOnly("이디야");
        assertThat(content.get(0).getShopTags()).hasSize(3);
        assertThat(content.get(0).getShopTags()).extracting("tag")
                                                .extracting("name")
                                                .containsOnly("포항", "한동대", "양덕");
    }

    private void initTags() {
        포항 = new Tag("포항");
        영일대 = new Tag("영일대");
        한동대 = new Tag("한동대");
        양덕 = new Tag("양덕");
        entityManager.persist(포항);
        entityManager.persist(영일대);
        entityManager.persist(한동대);
        entityManager.persist(양덕);
    }

    private void initCategory() {
        식당 = new Category("식당");
        카페 = new Category("카페");
        숙소 = new Category("숙소");
        한식 = new Category("한식", 식당);
        분식 = new Category("분식", 식당);
        양식 = new Category("양식", 식당);
        프랜차이즈 = new Category("프랜차이즈", 카페);
        개인카페 = new Category("개인카페", 카페);
        entityManager.persist(식당);
        entityManager.persist(카페);
        entityManager.persist(숙소);
        entityManager.persist(한식);
        entityManager.persist(분식);
        entityManager.persist(양식);
        entityManager.persist(프랜차이즈);
        entityManager.persist(개인카페);
    }

    private void initShops() {
        Shop 팜스발리 = createRestaurant("팜스발리", 5.0, Arrays.asList(포항, 한동대), 식당, Arrays.asList(분식, 양식), randomShopImages());
        Shop 바벤 = createRestaurant("바벤", 4.9, Arrays.asList(포항, 양덕), 식당, Arrays.asList(한식, 양식), randomShopImages());
        Shop 할매국밥 = createRestaurant("할매국밥", 4.8, Arrays.asList(포항, 양덕), 식당, Collections.singletonList(한식), randomShopImages());
        Shop 엽떡 = createRestaurant("엽떡", 4.7, Arrays.asList(포항, 양덕), 식당, Collections.singletonList(분식), randomShopImages());
        Shop 도토리 = createRestaurant("도토리", 4.6, Arrays.asList(포항, 양덕), 식당, Collections.singletonList(한식), randomShopImages());
        Shop 가온밀면 = createRestaurant("가온밀면", 4.5, Arrays.asList(포항, 양덕), 식당, Collections.singletonList(한식), randomShopImages());
        Shop 이디야 = createCafe("이디야", 4.7, Arrays.asList(포항, 한동대, 양덕), 카페, Collections.singletonList(프랜차이즈), randomShopImages());
        Shop 스타벅스 = createCafe("이디야", 4.8, Arrays.asList(포항, 영일대), 카페, Collections.singletonList(프랜차이즈), randomShopImages());
        Shop 유야 = createCafe("커피유야", 4.9, Arrays.asList(포항, 양덕), 카페, Collections.singletonList(개인카페), randomShopImages());
        entityManager.persist(팜스발리);
        entityManager.persist(바벤);
        entityManager.persist(할매국밥);
        entityManager.persist(엽떡);
        entityManager.persist(도토리);
        entityManager.persist(가온밀면);
        entityManager.persist(이디야);
        entityManager.persist(스타벅스);
        entityManager.persist(유야);
    }

    private Shop createRestaurant(
            String name,
            double star,
            List<Tag> tags,
            Category category,
            List<Category> subs,
            List<ShopImage> images
    ) {
        Restaurant restaurant = Restaurant.builder()
                                          .shopContent(new ShopContent(name))
                                          .shopScore(new ShopScore(star))
                                          .shopImages(new ShopImages(images))
                                          .build();
        restaurant.addTags(tags);
        restaurant.addCategories(category, subs);
        return restaurant;
    }

    private Shop createCafe(
            String name,
            double star,
            List<Tag> tags,
            Category category,
            List<Category> subs,
            List<ShopImage> images
    ) {
        Cafe cafe = Cafe.builder()
                        .shopContent(new ShopContent(name))
                        .shopScore(new ShopScore(star))
                        .shopImages(new ShopImages(images))
                        .build();
        cafe.addTags(tags);
        cafe.addCategories(category, subs);
        return cafe;
    }

    private List<ShopImage> randomShopImages() {
        List<ShopImage> images = new ArrayList<>();
        Random random = new Random();
        images.add(getRandomImageUrl(random, true));
        images.add(getRandomImageUrl(random, false));
        return images;
    }

    private ShopImage getRandomImageUrl(Random random, boolean isMain) {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;
        String url = random.ints(leftLimit, rightLimit + 1)
                           .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                           .limit(targetStringLength)
                           .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                           .toString();
        return new ShopImage(url, isMain);
    }
}
