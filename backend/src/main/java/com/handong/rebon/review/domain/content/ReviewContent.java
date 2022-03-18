package com.handong.rebon.review.domain.content;

import com.handong.rebon.exception.review.ReviewContentFormatException;
import com.handong.rebon.exception.review.ReviewTitleFormatException;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

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
        isNotValidTitleAndContent(title, content);
        this.title = title;
        this.content = content;
        this.tip = tip;
    }

    private boolean isNotValidData(String data) {
        return Objects.isNull(data) ||
                data.isBlank();
    }

    private void isNotValidTitleAndContent(String title, String content) {
        if (isNotValidData(title)) {
            throw new ReviewTitleFormatException();
        }
        if (isNotValidData(content)) {
            throw new ReviewContentFormatException();
        }
    }
}
