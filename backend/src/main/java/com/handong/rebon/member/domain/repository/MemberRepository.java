package com.handong.rebon.member.domain.repository;

import com.handong.rebon.member.domain.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
