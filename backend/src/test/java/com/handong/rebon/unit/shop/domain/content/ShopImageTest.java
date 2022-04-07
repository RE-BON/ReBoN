package com.handong.rebon.unit.shop.domain.content;

import java.util.Arrays;
import java.util.Collections;

import com.handong.rebon.shop.domain.content.ShopImage;
import com.handong.rebon.shop.domain.content.ShopImages;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.handong.rebon.shop.domain.content.ShopImages.DEFAULT_IMAGE_URL;
import static org.assertj.core.api.Assertions.assertThat;

class ShopImageTest {

    @Test
    @DisplayName("메인 이미지를 반환한다.")
    void mainImage() {
        // given
        ShopImages shopImages = new ShopImages(Arrays.asList(
                new ShopImage("url1", false),
                new ShopImage("url2", true),
                new ShopImage("url3", false)
        ));

        // when
        String url = shopImages.mainImage();

        // then
        assertThat(url).isEqualTo("url2");
    }

    @Test
    @DisplayName("메인 이미지가 없다면 기본 이미지를 반환한다.")
    void noMainImage() {
        // given
        ShopImages shopImages = new ShopImages(Collections.emptyList());

        // when
        String url = shopImages.mainImage();

        // then
        assertThat(url).isEqualTo(DEFAULT_IMAGE_URL);
    }
}
