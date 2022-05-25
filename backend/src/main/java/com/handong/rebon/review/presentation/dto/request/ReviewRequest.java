package com.handong.rebon.review.presentation.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    private String content;
    private String tip;
    private List<String> imageUrls = new ArrayList<>();
    private int star;

    public ReviewRequest(String content, String tip, int star) {
        this.content = content;
        this.tip = tip;
        this.star = star;
    }
}
