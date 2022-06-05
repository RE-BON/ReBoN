package com.handong.rebon.review.domain.empathy;

import java.util.Objects;
import javax.persistence.*;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.review.domain.Review;

import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
public class Empathy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Review review;

    public Empathy(Member member, Review review) {
        this.member = member;
        this.review = review;
    }

    public boolean isSameMember(Member member) {
        return this.member.isSame(member);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Empathy))
            return false;
        Empathy empathy = (Empathy) o;
        return Objects.equals(member, empathy.member) && Objects.equals(review, empathy.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, review);
    }
}
