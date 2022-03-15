package com.handong.rebon.review.domain.content;

import com.handong.rebon.exception.review.ReviewContentFormatException;
import com.handong.rebon.exception.review.ReviewTitleFormatException;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
public class ReviewContent {

    private String title;
    private String content;
    private String tip;

    protected ReviewContent() {
    }

    public ReviewContent(String title, String content) {
        IsNotValidtitleAndContent(title, content);
        this.title = title;
        this.content = content;
    }

    public ReviewContent(String title, String content, String tip) {
        this(title,content);
        this.tip = tip;
    }

    private boolean isNotValidTitle(String title) {
        return Objects.isNull(title) ||
                title.isBlank();
    }

    private boolean isNotValidContent(String content) {
        return Objects.isNull(content) ||
                content.isBlank();
    }

    private void IsNotValidtitleAndContent(String title, String content) throws ReviewTitleFormatException, ReviewContentFormatException {
        if(isNotValidTitle(title)){
            throw new ReviewTitleFormatException();
        }
        if(isNotValidContent(content)){
            throw new ReviewContentFormatException();
        }
    }
}
