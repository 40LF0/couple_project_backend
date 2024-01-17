package com.example.spring.domain.review;

import com.example.spring.domain.review.domain.Review;
import com.example.spring.domain.review.dto.ReviewRequestDTO;
import com.example.spring.domain.review.dto.ReviewResponseDTO;
import com.example.spring.global.apiResponse.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("")
    public ApiResponse<Boolean> join(@RequestBody @Valid ReviewRequestDTO.ReviewSaveDto request){
        Review review = reviewService.createReview(request);
        return ApiResponse.onSuccess(Boolean.TRUE);
    }

    @GetMapping("")
    public ApiResponse<ReviewResponseDTO.ReviewEntityDTO> get(@RequestParam Long reviewId){
        ReviewResponseDTO.ReviewEntityDTO dto = reviewService.getReviewInfo(reviewId);
        return ApiResponse.onSuccess(dto);
    }
}
