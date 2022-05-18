package com.handong.rebon.integration.category;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

import com.handong.rebon.category.application.dto.request.CategoryRequestDto;
import com.handong.rebon.category.domain.Category;
import com.handong.rebon.exception.category.CategoryNotFoundException;
import com.handong.rebon.shop.domain.Shop;
import com.handong.rebon.shop.domain.content.ShopContent;
import com.handong.rebon.shop.domain.content.ShopImages;
import com.handong.rebon.shop.domain.content.ShopScore;
import com.handong.rebon.shop.domain.repository.ShopRepository;
import com.handong.rebon.shop.domain.type.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CategoryDeleteIntegrationTest extends CategoryIntegrationTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ShopRepository shopRepository;

    @Test
    @DisplayName("자식 카테고리를 삭제한다.")
    public void 자식_카테고리_삭제() {

        //given
        String parentName = "식당";
        String childName = "한식";
        String childName2 = "중식";
        Category parent = createCategory(parentName);
        Category child = createCategoryWithParent(parent.getId(), childName);
        createCategoryWithParent(parent.getId(), childName2);
        categoryService.delete(new CategoryRequestDto(child.getId()));
        entityManager.flush();
        entityManager.clear();
        //then
        assertThat(categoryService.findById(parent.getId()).getChildren())
                .extracting("name")
                .containsOnly("중식");
        assertThat(categoryService.findById(parent.getId()).getChildren())
                .extracting("name")
                .doesNotContain("한식");
    }

    @Test
    @DisplayName("루트 카테고리를 삭제하면 자식 카테고리들까지 모두 삭제된다.")
    public void 루트_카테고리_삭제() {

        //given
        String parentName = "식당";
        Category parent = createCategory(parentName);
        Category child = createCategoryWithParent(parent.getId(), "한식");
        createCategoryWithParent(parent.getId(), "중식");
        createCategoryWithParent(parent.getId(), "양식");

        //when
        categoryService.delete(new CategoryRequestDto(parent.getId()));
        entityManager.flush();
        entityManager.clear();
        //then


        assertThatThrownBy(() -> categoryService.findById(parent.getId()))
                .isInstanceOf(CategoryNotFoundException.class);
        assertThatThrownBy(() -> categoryService.findById(child.getId()))
                .isInstanceOf(CategoryNotFoundException.class);
    }

    @Test
    @DisplayName("이미 삭제된 카테고리를 삭제요청 할 경우 예외 발생")
    public void 삭제된_카테고리_삭제() {

        //given
        String parentName = "식당";
        Category parent = createCategory(parentName);
        Category child = createCategoryWithParent(parent.getId(), "한식");
        CategoryRequestDto requestDto = new CategoryRequestDto(child.getId());
        categoryService.delete(requestDto);

        //when, then
        assertThatThrownBy(() -> categoryService.delete(requestDto))
                .isInstanceOf(CategoryNotFoundException.class);

    }

    @Test
    @DisplayName("이미 삭제된 카테고리를 조회 요청 할 경우 예외 발생")
    public void 삭제된_카테고리_조회() {

        //given
        String parentName = "식당";
        Category parent = createCategory(parentName);
        Category child = createCategoryWithParent(parent.getId(), "한식");
        CategoryRequestDto requestDto = new CategoryRequestDto(child.getId());
        categoryService.delete(requestDto);
        entityManager.flush();
        entityManager.clear();

        //when, then
        assertThatThrownBy(() -> categoryService.findById(requestDto.getId()))
                .isInstanceOf(CategoryNotFoundException.class);
    }

    @Test
    @DisplayName("카테고리를 삭제하면 ShopCategory도 같이 삭제된다.")
    public void deleteCategoryAndShopCateogory() {

        //given
        Category rootCategory = createCategory("식당");
        Category category = createCategory("한식");
        List<Category> subCategories = Arrays.asList(
                category,
                createCategory("분식")
        );
        ShopContent content = ShopContent.builder()
                                         .name("팜스발리")
                                         .build();
        Restaurant restaurant1 = Restaurant.builder()
                                           .shopContent(content)
                                           .shopScore(new ShopScore())
                                           .shopImages(new ShopImages())
                                           .build();
        restaurant1.addCategories(rootCategory, subCategories);
        shopRepository.save(restaurant1);
        //when
        categoryService.delete(new CategoryRequestDto(category.getId()));
        //then
        assertThat(category.isDeleted()).isEqualTo(true);
        assertThat(category.getShopCategories()).extracting("deleted")
                                                .containsOnly(true);
    }

    @Test
    @DisplayName("루트 카테고리를 삭제하면 해당 카테고리에 포함된 모든 Shop을 삭제한다.")
    public void 루트카테고리_상점_삭제() {

        //given
        Category rootCategory = createCategory("식당");
        List<Category> subCategories = Arrays.asList(
                createCategory("한식"),
                createCategory("분식")
        );
        ShopContent content = ShopContent.builder()
                                         .name("팜스발리")
                                         .build();
        ShopContent content2 = ShopContent.builder()
                                          .name("맘스키친")
                                          .build();

        Restaurant restaurant1 = Restaurant.builder()
                                           .shopContent(content)
                                           .shopScore(new ShopScore())
                                           .shopImages(new ShopImages())
                                           .build();
        Restaurant restaurant2 = Restaurant.builder()
                                           .shopContent(content2)
                                           .shopScore(new ShopScore())
                                           .shopImages(new ShopImages())
                                           .build();
        restaurant1.addCategories(rootCategory, subCategories);
        restaurant2.addCategories(rootCategory, subCategories);
        Restaurant savedRestaurant1 = shopRepository.save(restaurant1);
        Restaurant savedRestaurant2 = shopRepository.save(restaurant2);
        boolean beforeDelete1 = savedRestaurant1.isDeleted();
        boolean beforeDelete2 = savedRestaurant2.isDeleted();
        CategoryRequestDto requestDto = new CategoryRequestDto(rootCategory.getId());
        //when
        categoryService.delete(requestDto);
        entityManager.flush();
        entityManager.clear();
        Optional<Shop> deletedRestaurant1 = shopRepository.findById(savedRestaurant1.getId());
        Optional<Shop> deletedRestaurant2 = shopRepository.findById(savedRestaurant2.getId());
        Optional<Category> deletedCategory = categoryRepository.findById(rootCategory.getId());
        //then
        assertThat(beforeDelete1).isFalse();
        assertThat(beforeDelete2).isFalse();
        assertThat(deletedRestaurant1).isEmpty();
        assertThat(deletedRestaurant2).isEmpty();
        assertThat(deletedCategory).isEmpty();
    }

}
