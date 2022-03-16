package com.handong.rebon.review.presentation;

import com.handong.rebon.review.application.ReviewService;
import com.handong.rebon.review.presentation.dto.ReviewRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/reviews")
@Controller
public class AdminReviewController {

    private final ReviewService reviewService;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("reviewRequest", new ReviewRequest());
        return "reviews/createReviewForm";
    }

    @PostMapping("/new")
    public void create(String title, String content, String tip) {
        ReviewRequest reviewRequest = new ReviewRequest(title, content, tip);
        Long id = reviewService.adminReviewCreate(reviewRequest.toAdminReviewCreateDto());
        log.info("id: " + id);
    }
}
