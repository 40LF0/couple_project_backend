package com.example.spring.domain.review;

import com.example.spring.domain.review.domain.Review;
import com.example.spring.domain.review.dto.ReviewRequestDTO;
import com.example.spring.domain.review.dto.ReviewResponseDTO;
import com.example.spring.global.apiResponse.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewConverter reviewConverter;

    @PostMapping("")
    public ApiResponse<ReviewResponseDTO.ReviewEntityDTO> postReview(@RequestBody @Valid ReviewRequestDTO.ReviewDTO request){
        Review review = reviewService.createReview(request);
        return ApiResponse.onSuccess(reviewConverter.toReviewEntityDTO(review));
    }

    @GetMapping("/{reviewId}")
    public ApiResponse<ReviewResponseDTO.ReviewEntityDTO> getReview(@PathVariable Long reviewId){
        Review review = reviewService.findById(reviewId);
        return ApiResponse.onSuccess(reviewConverter.toReviewEntityDTO(review));
    }

    @PutMapping("/{reviewId}")
    public ApiResponse<ReviewResponseDTO.ReviewEntityDTO> putReview(@RequestBody @Valid ReviewRequestDTO.ReviewDTO request, @PathVariable Long reviewId) {
        Review review = reviewService.updateReview(request, reviewId);
        return ApiResponse.onSuccess(reviewConverter.toReviewEntityDTO(review));
    }

    @GetMapping("/previews")
    public ApiResponse<Page<ReviewResponseDTO.PreviewDTO>> getPreviewList(@Nullable @RequestParam Long memberId, Pageable pageable){
        Page<ReviewResponseDTO.PreviewDTO> previews = reviewService.getPreviewList(memberId,pageable);
        return ApiResponse.onSuccess(previews);
    }
}
