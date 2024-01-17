package com.example.spring.domain.review;

import com.example.spring.domain.member.domain.Member;
import com.example.spring.domain.member.MemberService;
import com.example.spring.domain.review.domain.Review;
import com.example.spring.domain.review.dto.ReviewRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberService memberService;
    @Transactional
    public Review createReview(ReviewRequestDTO.ReviewSaveDto request) {
        Review review = toReviewEntity(request);
        reviewRepository.save(review);
        return review;
    }

    private Review toReviewEntity(ReviewRequestDTO.ReviewSaveDto request) {
        Member member = memberService.findById(request.getMemberId());
        Review review = Review.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .member(member)
                .build();
        review.updateReviewImages(request.getImageUrlList());
        review.updateSpots(request.getSpotList());
        return review;
    }
}
