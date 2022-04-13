package com.handong.rebon.member.domain.repository;

import java.util.Optional;

import com.handong.rebon.auth.domain.OauthProvider;
import com.handong.rebon.member.domain.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where m.profile.email =:email and m.oauthProvider:= oauthProvide")
    Optional<Member> findByEmail(@Param("email") String email, @Param("oauthProvider")OauthProvider oauthProvider);
}
