package com.handong.rebon.review.application.dto.request;

import java.util.List;

import com.handong.rebon.review.domain.content.ReviewContent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReviewUpdateRequestDto {
    private Long memberId;
    private Long reviewId;
    private String content;
    private String tip;
    private List<String> imageUrls;
    private int star;

    public ReviewContent getReviewContent() {
        return new ReviewContent(content, tip);
    }
}
