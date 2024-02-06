package com.example.spring.domain.review;

import com.example.spring.domain.member.application.MemberService;
import com.example.spring.domain.member.domain.Member;
import com.example.spring.domain.review.domain.Review;
import com.example.spring.domain.review.dto.ReviewRequestDTO;
import com.example.spring.domain.review.dto.ReviewResponseDTO;
import com.example.spring.global.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewConverter {
    private final MemberService memberService;
    public ReviewResponseDTO.ReviewEntityDTO toReviewEntityDTO(Review review) {
        return ReviewResponseDTO.ReviewEntityDTO
                .builder()
                .reviewId(review.getReviewId())
                .title(review.getTitle())
                .body(review.getBody())
                .likeCount(review.getLikeCount())
                .imageUrlList(review.fetchImageUrlList())
                .spotList(review.fetchSpotList())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public Review toReviewEntity(ReviewRequestDTO.ReviewDTO request) {
        Member member = memberService.findByEmail(SecurityUtil.getCurrentMemberId());
        Review review = Review.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .member(member)
                .build();
        review.updateReviewImages(request.getImageUrlList());
        review.updateSpots(request.getSpotList());
        return review;
    }
    public Page<ReviewResponseDTO.PreviewDTO> toPreviewListDto(Page<Review> reviews) {
        return reviews.map(review -> {
            // Extract the first image URL or null if the list is empty
            String imageUrl = review.getReviewImageList().getFirst().getImgUrl();

            // Extract the member ID
            Long memberId = review.getMember() != null ? review.getMember().getMemberId() : null;

            // Create and return the PreviewDTO
            return ReviewResponseDTO.PreviewDTO.builder()
                    .reviewId(review.getReviewId())
                    .memberId(memberId)
                    .title(review.getTitle())
                    .likeCount(review.getLikeCount())
                    .spotList(review.fetchSpotList())
                    .imageUrl(imageUrl)
                    .createdAt(review.getCreatedAt())
                    .build();
        });
    }
}
