package com.handong.rebon.member.domain.repository;

import java.util.Optional;

import com.handong.rebon.member.domain.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByProfileEmailAndOauthProvider(String email, String oauthProvider);

    boolean existsByProfileNickname(String nickname);
}
