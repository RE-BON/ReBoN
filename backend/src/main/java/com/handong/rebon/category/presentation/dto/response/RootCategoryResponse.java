package com.handong.rebon.category.presentation.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RootCategoryResponse {
    private Long id;
    private String name;
    private List<ChildCategoryResponse> children;
}
