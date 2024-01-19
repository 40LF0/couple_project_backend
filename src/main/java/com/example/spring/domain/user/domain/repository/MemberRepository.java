package com.example.spring.domain.user.domain.repository;

import com.example.spring.domain.user.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Boolean existsByEmail(String email);

    @EntityGraph(attributePaths = "authorities")
    Optional<Member> findByEmail(String email);

//    Optional<Member> findMemberByUsernameAndRefreshToken(String username, String refreshToken);

}