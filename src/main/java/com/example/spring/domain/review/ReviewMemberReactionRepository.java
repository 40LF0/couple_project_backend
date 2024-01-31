package com.example.spring.domain.review;

import com.example.spring.domain.review.domain.ReviewMemberReaction;
import com.example.spring.domain.review.domain.ReviewMemberReactionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewMemberReactionRepository extends JpaRepository<ReviewMemberReaction, ReviewMemberReactionId> {
}
