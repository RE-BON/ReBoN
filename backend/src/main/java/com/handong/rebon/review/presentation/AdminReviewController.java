package com.handong.rebon.review.presentation;

import com.handong.rebon.review.application.ReviewService;
import com.handong.rebon.review.domain.Review;
import com.handong.rebon.review.presentation.dto.AdminReviewPostRequest;
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
        model.addAttribute("adminReviewPostRequest", new AdminReviewPostRequest());
        return "reviews/createForm";
    }

    @PostMapping("/new")
    public String create(AdminReviewPostRequest adminReviewPostRequest) {
        Long id = reviewService.adminReviewCreate(adminReviewPostRequest.toAdminReviewCreateDto());
        log.info("id: " + id);
        return "";
    }
}
