package com.handong.rebon.review.presentation.dto;

import com.handong.rebon.review.application.dto.request.AdminReviewPostRequestDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminReviewPostRequest {
    private Long id;
    private String title;
    private String content;
    private String tip;

    public AdminReviewPostRequestDto toAdminReviewCreateDto() {
        return AdminReviewPostRequestDto.builder()
                                          .title(title)
                                          .content(content)
                                          .tip(tip)
                                          .build();
    }
}
