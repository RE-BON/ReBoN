package com.handong.rebon.review.presentation;

import com.handong.rebon.review.application.ReviewService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/admin/reviews")
@Controller
public class AdminReviewController {

    private final ReviewService reviewService;

}
