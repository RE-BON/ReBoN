package com.handong.rebon.shop.presentation.dto.request;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.handong.rebon.shop.application.dto.request.ShopRequestDto;
import com.handong.rebon.util.StringUtil;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShopRequest {
    private Long categoryId;
    private List<Long> subCategories = new ArrayList<>();
    private String name;
    private String businessHour;
    private String phone;
    private String address;
    private String latitude;
    private String longitude;
    private List<MultipartFile> images = new ArrayList<>();
    private List<Long> tags;
    private String menus;

    public ShopRequestDto toDto() {
        LocalTime[] businessHour = StringUtil.getTime(this.businessHour);

        return ShopRequestDto.builder()
                             .categoryId(categoryId)
                             .subCategories(subCategories)
                             .name(name)
                             .start(businessHour[0])
                             .end(businessHour[1])
                             .phone(phone)
                             .address(address)
                             .latitude(latitude)
                             .longitude(longitude)
                             .images(images)
                             .tags(tags)
                             .menus(menus)
                             .build();
    }

}
