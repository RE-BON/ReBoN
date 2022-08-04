package com.handong.rebon.common;

import java.util.*;
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
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import static com.handong.rebon.util.StringUtil.validatesEmptyList;

@RequiredArgsConstructor
@Component
@Profile("prod")
public class DataInitializer implements ApplicationRunner {
    public static Pattern HOUR_PATTERN = Pattern.compile("([0-9]+:[0-9]+~[0-9]+:[0-9]+)");
    private static List<String> INIT_QUERY = List.of("포항", "포항 양덕", "구룡포", "한동대");

    private final TagRepository tagRepository;
    private final TagSearchRepository tagSearchRepository;
    private final CategoryRepository categoryRepository;
    private final ShopRepository shopRepository;
    private final ShopAdapterService shopAdapterService;
    private final NaverShopInserter shopInserter;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Category 식당 = new Category("식당");
        Category 카페 = new Category("카페");
        Category 숙소 = new Category("숙소");
        categoryRepository.saveAll(List.of(식당, 카페, 숙소));

        List<Shop> shops = new ArrayList<>();
        for (String query : INIT_QUERY) {
            List<NaverShopDto> restaurants = shopInserter.getShops(식당, query, "식당");
            List<NaverShopDto> cafes = shopInserter.getShops(카페, query, "카페");
            List<NaverShopDto> lodgings = shopInserter.getShops(숙소, query, "숙소");
            //                        List<NaverShopDto> restaurants = shopInserter.getShopsWebClient(식당, query, "식당");
            //                        List<NaverShopDto> cafes = shopInserter.getShopsWebClient(카페, query, "카페");
            //                        List<NaverShopDto> lodgings = shopInserter.getShopsWebClient(숙소, query, "숙소");
            shops.addAll(getShops(restaurants, cafes, lodgings));
        }

        List<Shop> results = new ArrayList<>();
        for (Shop newShop : shops) {
            Optional<Shop> shop = shopRepository.findByNaverId(newShop.getNaverId());

            if (shop.isEmpty()) {
                results.add(newShop);
                continue;
            }

            Shop existingShop = shop.get();
            List<Tag> tags = newShop.getTags();
            List<Category> categories = newShop.getSubcategories();
            existingShop.updateTags(tags);
            existingShop.updateCategories(existingShop.getCategory(), categories);
        }

        shopRepository.saveAll(results);
    }

    private List<Shop> getShops(List<NaverShopDto>... shops) {
        return Arrays.stream(shops)
                     .flatMap(Collection::stream)
                     .map(NaverShopDto::getAllShops)
                     .flatMap(Collection::stream)
                     .filter(dto -> validatesEmptyList(dto.getShortAddress()))
                     .filter(dto -> containKeyword(dto.getShortAddress(), "포항"))
                     .map(dto -> {
                         Category category = dto.getMainCategory();
                         List<Category> subCategories = getSubCategories(category, dto.getCategory());
                         List<Tag> tags = getTags(dto.getTagCandidates());
                         ShopImages shopImages = new ShopImages(List.of(new ShopImage(dto.getThumUrl(), true)));

                         ShopServiceAdapter adapter = shopAdapterService.shopAdapterByCategory(category);
                         Shop shop = adapter.createNaverShop(shopImages, dto);

                         shop.addTags(tags);
                         shop.addCategories(category, subCategories);

                         return shop;
                     }).collect(Collectors.toList());
    }

    private boolean containKeyword(List<String> address, String keyword) {
        String basicAddress = address.get(0);
        return basicAddress.contains("포항");
    }

    //    private boolean validatesAddress(List<String> address) {
    //        if (Objects.isNull(address) || address.isEmpty()) {
    //            return false;
    //        }
    //        String basicAddress = address.get(0);
    //        return basicAddress.contains("포항");
    //    }

    private List<Tag> getTags(String address) {
        List<Tag> tags = new ArrayList<>();

        String[] addressUnits = address.split(" ");
        for (String name : addressUnits) {
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
        if (Objects.isNull(categories) || categories.isEmpty()) {
            return subCategories;
        }

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
