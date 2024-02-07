package com.example.spring.domain.review;

import com.example.spring.domain.review.domain.Review;
import com.example.spring.domain.review.dto.ReviewRequestDTO;
import com.example.spring.global.apiResponse.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/")
    public ApiResponse<Boolean> join(@RequestBody @Valid ReviewRequestDTO.ReviewSaveDto request){
        Review review = reviewService.createReview(request);
        return ApiResponse.onSuccess(Boolean.TRUE);
    }
}
