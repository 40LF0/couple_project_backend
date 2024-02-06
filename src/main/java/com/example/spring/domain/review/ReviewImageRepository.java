package com.example.spring.domain.review;

import com.example.spring.domain.review.domain.Review;
import com.example.spring.domain.review.domain.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewImageRepository extends JpaRepository<ReviewImage,Long> {
}
