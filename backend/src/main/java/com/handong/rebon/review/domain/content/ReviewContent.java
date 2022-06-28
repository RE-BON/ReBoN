package com.handong.rebon.review.domain.content;

import java.util.Objects;
import javax.persistence.Embeddable;

import com.handong.rebon.exception.review.ReviewContentFormatException;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class ReviewContent {
    private String content;
    private String tip;

    public ReviewContent(String content) {
        this(content, "");
    }

    public ReviewContent(String content, String tip) {
        validateContent(content);
        this.content = content;
        this.tip = tip;
    }

    private void validateContent(String content) {
        if (validatesData(content)) {
            throw new ReviewContentFormatException();
        }
    }

    private boolean validatesData(String data) {
        return Objects.isNull(data) ||
                data.isBlank();
    }

}
