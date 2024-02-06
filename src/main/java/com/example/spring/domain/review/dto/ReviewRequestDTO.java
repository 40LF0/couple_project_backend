package com.example.spring.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

public class ReviewRequestDTO {
    @Getter
    public static class ReviewDTO {
        @NotBlank
        String title;
        @NotBlank
        String body;
        @NotEmpty
        List<String> spotList;
        @NotEmpty
        List<String> imageUrlList;
    }
}
