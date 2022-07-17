package com.handong.rebon.common;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.handong.rebon.category.domain.Category;
import com.handong.rebon.category.domain.repository.CategoryRepository;
import com.handong.rebon.shop.application.ShopAdapterService;
import com.handong.rebon.shop.application.adapter.ShopServiceAdapter;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.content.ShopImage;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.repository.ShopRepository;
import com.handong.rebon.shop.infrastructure.NaverShopInserter;
import com.handong.rebon.shop.infrastructure.dto.NaverShopDto;
import com.handong.rebon.tag.domain.Tag;
import com.handong.rebon.tag.domain.repository.TagRepository;
import com.handong.rebon.tag.domain.repository.TagSearchRepository;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@Profile("prod")
public class DataInitializer implements ApplicationRunner {
    public static Pattern HOUR_PATTERN = Pattern.compile("([0-9]+:[0-9]+~[0-9]+:[0-9]+)");
    private static Pattern ADDRESS_PATTERN = Pattern.compile(".+도|.+시|.+군|.+구|.+동|.+읍|.+면|.+리|.+로");

    private final TagRepository tagRepository;
    private final TagSearchRepository tagSearchRepository;
    private final CategoryRepository categoryRepository;
    private final ShopRepository shopRepository;
    private final ShopAdapterService shopAdapterService;
    private final NaverShopInserter shopInserter;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Category 식당 = new Category("식당");
        Category 카페 = new Category("카페");
        Category 숙소 = new Category("숙소");
        categoryRepository.saveAll(List.of(식당, 카페, 숙소));

        List<NaverShopDto> restaurants = shopInserter.getShops(식당, "포항 식당");
        List<NaverShopDto> cafes = shopInserter.getShops(카페, "포항 카페");
        List<NaverShopDto> lodgings = shopInserter.getShops(숙소, "포항 숙소");

        List<Shop> shops = getShops(restaurants, cafes, lodgings);

        shopRepository.saveAll(shops);
    }

    private List<Shop> getShops(List<NaverShopDto>... shops) {
        return Arrays.stream(shops)
                     .flatMap(Collection::stream)
                     .map(NaverShopDto::getAllShops)
                     .flatMap(Collection::stream)
                     .map(dto -> {
                         Category category = dto.getMainCategory();
                         List<Category> subCategories = getSubCategories(category, dto.getCategory());
                         List<Tag> tags = getTags(dto.getRoadAddress());
                         ShopImages shopImages = new ShopImages(List.of(new ShopImage(dto.getThumUrl(), true)));

                         ShopServiceAdapter adapter = shopAdapterService.shopAdapterByCategory(category);
                         Shop shop = adapter.createNaverShop(shopImages, dto);

                         shop.addTags(tags);
                         shop.addCategories(category, subCategories);

                         return shop;
                     }).collect(Collectors.toList());
    }

    private List<Tag> getTags(String address) {
        List<Tag> tags = new ArrayList<>();

        Matcher matcher = ADDRESS_PATTERN.matcher(address);
        while (matcher.find()) {
            String name = matcher.group().strip();
            Optional<Tag> tag = tagRepository.findByName(name);

            if (tag.isEmpty()) {
                Tag newTag = tagRepository.save(new Tag(name));
                tagSearchRepository.save(newTag);
                tags.add(newTag);
                continue;
            }

            tags.add(tag.get());
        }

        return tags;
    }

    private List<Category> getSubCategories(Category mainCategory, List<String> categories) {
        List<Category> subCategories = new ArrayList<>();

        categories.stream()
                  .map(s -> s.split(","))
                  .flatMap(Arrays::stream)
                  .forEach(name -> {
                      Optional<Category> category = categoryRepository.findByName(name);

                      if (category.isEmpty()) {
                          Category newCategory = categoryRepository.save(new Category(name, mainCategory));
                          subCategories.add(newCategory);
                      } else {
                          subCategories.add(category.get());
                      }
                  });

        return subCategories;
    }
}
