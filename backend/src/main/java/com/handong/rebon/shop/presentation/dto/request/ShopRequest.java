package com.handong.rebon.shop.presentation.dto.request;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.handong.rebon.shop.application.dto.request.ShopRequestDto;
import com.handong.rebon.shop.application.dto.request.menu.MenuGroupRequestDto;
import com.handong.rebon.shop.application.dto.request.menu.MenuRequestDto;
import com.handong.rebon.util.StringUtil;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShopRequest {
    private final static int DEFAULT_MENU_SIZE = 3;

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
    private List<MenuGroupRequest> menus = new ArrayList<>();

    public ShopRequest(int menuGroupSize) {
        for (int i = 0; i < menuGroupSize; i++) {
            menus.add(new MenuGroupRequest(DEFAULT_MENU_SIZE));
        }
    }

    public ShopRequestDto toDto() {
        List<MenuGroupRequestDto> menuGroupRequestDtos = menus.stream()
                                                              .filter(menuGroup -> !menuGroup.getName().isBlank())
                                                              .map(this::toDto)
                                                              .collect(Collectors.toList());
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
                             .menus(menuGroupRequestDtos)
                             .build();
    }

    private MenuGroupRequestDto toDto(MenuGroupRequest menuGroupRequest) {
        List<MenuRequestDto> menuRequestDtos = menuGroupRequest
                .getMenus()
                .stream()
                .filter(menu -> !menu.getName().isBlank())
                .map(menuRequest -> new MenuRequestDto(menuRequest.getName(), menuRequest.getPrice()))
                .collect(Collectors.toList());
        return new MenuGroupRequestDto(menuGroupRequest.getName(), menuRequestDtos);
    }
}
