package com.handong.rebon.review.domain.content;

import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class ReviewImages {

    @OneToMany(mappedBy = "review")
    private List<ReviewImage> reviewImages;
}
