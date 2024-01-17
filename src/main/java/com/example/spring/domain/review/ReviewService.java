package com.example.spring.domain.review;

import com.example.spring.domain.member.domain.Member;
import com.example.spring.domain.member.MemberService;
import com.example.spring.domain.review.domain.Review;
import com.example.spring.domain.review.dto.ReviewRequestDTO;
import com.example.spring.domain.review.dto.ReviewResponseDTO;
import com.example.spring.global.apiResponse.code.status.ErrorStatus;
import com.example.spring.global.apiResponse.exception.GeneralException;
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

    public ReviewResponseDTO.ReviewEntityDTO getReviewInfo(Long reviewId) {
        Review review = findById(reviewId);
        return toReviewEntityDTO(review);
    }

    public Review findById(Long reviewId){
        return reviewRepository.findById(reviewId).orElseThrow(() ->
                new GeneralException(ErrorStatus.REVIEW_NOT_FOUND));
    }

    private ReviewResponseDTO.ReviewEntityDTO toReviewEntityDTO(Review review) {
        return ReviewResponseDTO.ReviewEntityDTO
                .builder()
                .reviewId(review.getReviewId())
                .title(review.getTitle())
                .body(review.getBody())
                .heart(review.getHeart())
                .imageUrlList(review.fetchImageUrlList())
                .spotList(review.fetchSpotList())
                .build();
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
