package com.handong.rebon.category.presentation.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RootCategoryResponse {
    private Long id;
    private String name;
    private List<ChildCategoryResponse> children;
}
