package com.example.spring.domain.review;

import com.example.spring.domain.member.domain.Member;
import com.example.spring.domain.member.application.MemberService;
import com.example.spring.domain.review.domain.Review;
import com.example.spring.domain.review.domain.ReviewMemberReaction;
import com.example.spring.domain.review.domain.ReviewMemberReactionId;
import com.example.spring.domain.review.dto.ReviewRequestDTO;
import com.example.spring.domain.review.dto.ReviewResponseDTO;
import com.example.spring.global.apiResponse.code.status.ErrorStatus;
import com.example.spring.global.apiResponse.exception.GeneralException;
import com.example.spring.global.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final MemberService memberService;
    private final ReviewConverter reviewConverter;
    private final ReviewMemberReactionRepository reviewMemberReactionRepository;
    @Transactional
    public Review createReview(ReviewRequestDTO.ReviewDTO request) {
        Review review = reviewConverter.toReviewEntity(request);
        reviewRepository.save(review);
        return review;
    }

    @Transactional
    public Review updateReview(ReviewRequestDTO.ReviewDTO request, Long reviewId) {
        Review review = findById(reviewId);
        Member member = memberService.findByEmail(SecurityUtil.getCurrentMemberId());
        if (!Objects.equals(review.getMember().getMemberId(), member.getMemberId())){
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


    public Page<ReviewResponseDTO.PreviewDTO> getPreviewList(Long memberId, Pageable pageable) {
        if(memberId != null){
            return getMyPreviewList(memberId, pageable);
        }
        Page<Review> reviews = reviewRepository.findAll(pageable);
        return reviewConverter.toPreviewListDto(reviews);
    }

    private Page<ReviewResponseDTO.PreviewDTO> getMyPreviewList(Long memberId, Pageable pageable) {
        Member member = memberService.findById(memberId);
        Page<Review> reviews = reviewRepository.findAllByMember(member,pageable);
        return reviewConverter.toPreviewListDto(reviews);
    }

    @Transactional
    public void enableReviewLike(Long reviewId) {
        Review review = findById(reviewId);
        Member member = memberService.findByEmail(SecurityUtil.getCurrentMemberId());
        ReviewMemberReaction reaction = getReviewMemberReaction(review, member);
        reaction.enableLike();
    }

    @Transactional
    public void disableReviewLike(Long reviewId) {
        Review review = findById(reviewId);
        Member member = memberService.findByEmail(SecurityUtil.getCurrentMemberId());
        ReviewMemberReaction reaction = getReviewMemberReaction(review, member);
        reaction.disableLike();
    }

    private ReviewMemberReaction getReviewMemberReaction(Review review, Member member) {
        ReviewMemberReactionId reactionId = new ReviewMemberReactionId(review, member);
        Optional<ReviewMemberReaction> reactionOptional = reviewMemberReactionRepository.findById(reactionId);
        if (reactionOptional.isPresent()) {
            return reactionOptional.get();
        } else {
            ReviewMemberReaction reaction = ReviewMemberReaction.builder()
                    .review(reactionId.getReview())
                    .member(reactionId.getMember())
                    .build();
            return reviewMemberReactionRepository.save(reaction);
        }
    }
}
