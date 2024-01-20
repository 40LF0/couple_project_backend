package com.example.spring.domain.review;

import com.example.spring.domain.member.domain.Member;
import com.example.spring.domain.review.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    Page<Review> findAllByMember(Member member, Pageable pageable);
}
