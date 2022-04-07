package com.handong.rebon.category.application.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RootCategoryResponseDto {
    private Long id;
    private String name;
    private List<ChildCategoryDto> children;

}
