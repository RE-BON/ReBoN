package com.handong.rebon.review.application.dto.request;

import java.util.List;

import com.handong.rebon.review.domain.content.ReviewContent;
import com.handong.rebon.review.domain.content.ReviewScore;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewCreateRequestDto {
    private Long memberId;
    private Long shopId;
    private String content;
    private String tip;
    private List<MultipartFile> images;
    private int star;

    public ReviewContent getReviewContent() {
        return new ReviewContent(content, tip);
    }

    public ReviewScore getReviewScore() {
        return new ReviewScore(star, 0);
    }
}
