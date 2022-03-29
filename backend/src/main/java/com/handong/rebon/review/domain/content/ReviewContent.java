package com.handong.rebon.review.domain.content;

import java.util.Objects;
import javax.persistence.Embeddable;

import com.handong.rebon.exception.review.ReviewContentFormatException;
import com.handong.rebon.exception.review.ReviewTitleFormatException;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class ReviewContent {
    private String title;
    private String content;
    private String tip;

    public ReviewContent(String title, String content) {
        this(title, content, "");
    }

    public ReviewContent(String title, String content, String tip) {
        validateTitleAndContent(title, content);
        this.title = title;
        this.content = content;
        this.tip = tip;
    }

    private void validateTitleAndContent(String title, String content) {
        if (validatesData(title)) {
            throw new ReviewTitleFormatException();
        }
        if (validatesData(content)) {
            throw new ReviewContentFormatException();
        }
    }

    private boolean validatesData(String data) {
        return Objects.isNull(data) ||
                data.isBlank();
    }


}
