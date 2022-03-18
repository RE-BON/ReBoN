package com.handong.rebon.review.domain.content;

import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ReviewImages {

    @OneToMany(mappedBy = "review")
    private List<ReviewImage> reviewImages;
}
