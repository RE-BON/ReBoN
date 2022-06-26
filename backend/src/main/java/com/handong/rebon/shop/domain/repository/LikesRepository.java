package com.handong.rebon.shop.domain.repository;

import java.util.List;

import com.handong.rebon.member.domain.Member;
import com.handong.rebon.shop.domain.like.Likes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    List<Likes> findByMember(Member member);
}
