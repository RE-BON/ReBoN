package com.handong.rebon.review.presentation.dto;

import com.handong.rebon.review.application.dto.request.AdminReviewCreateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminReviewCreateRequest {
    private String title;
    private String content;
    private String tip;

    public AdminReviewCreateRequestDto toAdminReviewCreateDto() {
        return AdminReviewCreateRequestDto.builder()
                                          .title(title)
                                          .content(content)
                                          .tip(tip)
                                          .build();
    }
}
