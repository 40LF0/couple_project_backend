package com.example.spring.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewEntityDTO{
        Long reviewId;
        Long memberId;
        String title;
        String body;
        int likeCount;
        List<String> spotList;
        List<String> imageUrlList;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PreviewDTO{
        Long reviewId;
        Long memberId;
        String title;
        int likeCount;
        List<String> spotList;
        String imageUrl;
        LocalDateTime createdAt;
    }
}
