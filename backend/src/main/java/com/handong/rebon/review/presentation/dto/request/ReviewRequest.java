package com.handong.rebon.review.presentation.dto.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequest {
    private String content;
    private String tip;
    private List<MultipartFile> images = new ArrayList<>();
    private int star;
}
