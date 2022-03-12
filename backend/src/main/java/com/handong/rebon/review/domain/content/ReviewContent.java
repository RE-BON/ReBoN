package com.handong.rebon.review.domain.content;

import javax.persistence.Embeddable;

@Embeddable
public class ReviewContent {
    private String title;
    private String content;
    private String tip;
}
