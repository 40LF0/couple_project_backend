package com.example.spring.domain.review;

import com.example.spring.domain.member.domain.Member;
import com.example.spring.domain.member.MemberService;
import com.example.spring.domain.review.domain.Review;
import com.example.spring.domain.review.dto.ReviewRequestDTO;
import com.example.spring.domain.review.dto.ReviewResponseDTO;
import com.example.spring.global.apiResponse.code.status.ErrorStatus;
import com.example.spring.global.apiResponse.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final MemberService memberService;
    @Transactional
    public Review createReview(ReviewRequestDTO.ReviewDTO request) {
        Review review = toReviewEntity(request);
        reviewRepository.save(review);
        return review;
    }

    @Transactional
    public Review updateReview(ReviewRequestDTO.ReviewDTO request, Long reviewId) {
        Review review = findById(reviewId);
        if (!Objects.equals(review.getMember().getMemberId(), request.getMemberId())){
            throw new GeneralException(ErrorStatus.REVIEW_NOT_FOUND);
        }
        review.updateTitle(request.getTitle());
        review.updateBody(request.getBody());
        review.updateSpots(request.getSpotList());
        updateReviewImages(review, request.getImageUrlList());
        return review;
    }

    private void updateReviewImages(Review review, List<String> newImageUrlList) {
        reviewImageRepository.deleteAll(review.getReviewImageList());
        review.updateReviewImages(newImageUrlList);
    }

    public Review findById(Long reviewId){
        return reviewRepository.findById(reviewId).orElseThrow(() ->
                new GeneralException(ErrorStatus.REVIEW_NOT_FOUND));
    }

    public ReviewResponseDTO.ReviewEntityDTO toReviewEntityDTO(Review review) {
        return ReviewResponseDTO.ReviewEntityDTO
                .builder()
                .reviewId(review.getReviewId())
                .title(review.getTitle())
                .body(review.getBody())
                .heart(review.getHeart())
                .imageUrlList(review.fetchImageUrlList())
                .spotList(review.fetchSpotList())
                .createdAt(review.getCreatedAt())
                .build();
    }

    private Review toReviewEntity(ReviewRequestDTO.ReviewDTO request) {
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

    public Page<ReviewResponseDTO.PreviewDTO> getPreviewList(Long memberId, Pageable pageable) {
        if(memberId != null){
            return getMyPreviewList(memberId, pageable);
        }
        Page<Review> reviews = reviewRepository.findAll(pageable);
        return toPreviewListDto(reviews);
    }

    private Page<ReviewResponseDTO.PreviewDTO> getMyPreviewList(Long memberId, Pageable pageable) {
        Member member = memberService.findById(memberId);
        Page<Review> reviews = reviewRepository.findAllByMember(member,pageable);
        return toPreviewListDto(reviews);
    }
    private Page<ReviewResponseDTO.PreviewDTO> toPreviewListDto(Page<Review> reviews) {
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
                    .heart(review.getHeart())
                    .spotList(review.fetchSpotList())
                    .imageUrl(imageUrl)
                    .createdAt(review.getCreatedAt())
                    .build();
        });
    }
}
