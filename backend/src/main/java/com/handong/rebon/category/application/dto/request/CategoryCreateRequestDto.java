package com.handong.rebon.category.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateRequestDto {
    private Long parentId;
    private String name;
}
