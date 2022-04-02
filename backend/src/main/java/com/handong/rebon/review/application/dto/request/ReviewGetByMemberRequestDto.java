package com.handong.rebon.review.application.dto.request;

import org.springframework.data.domain.Pageable;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewGetByMemberRequestDto {
    private Long memberId;
    private Pageable pageable;
}
