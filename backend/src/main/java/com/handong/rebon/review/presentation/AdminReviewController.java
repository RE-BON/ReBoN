package com.handong.rebon.review.presentation;

import java.util.List;

import com.handong.rebon.review.application.ReviewService;
import com.handong.rebon.review.application.dto.request.AdminReviewGetRequestDto;
import com.handong.rebon.review.application.dto.response.AdminReviewResponseDto;
import com.handong.rebon.review.presentation.dto.request.ReviewSearchRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public String getReviews(
            @ModelAttribute("reviewSearchRequest") ReviewSearchRequest reviewSearchRequest,
            @PageableDefault Pageable pageable, Model model
    ) {
        AdminReviewGetRequestDto adminReviewGetRequestDto = new AdminReviewGetRequestDto(reviewSearchRequest.getKeyword(), pageable);
        List<AdminReviewResponseDto> reviews = reviewService.search(adminReviewGetRequestDto);
        model.addAttribute("reviews", reviews);
        return "review/list";
    }
}
