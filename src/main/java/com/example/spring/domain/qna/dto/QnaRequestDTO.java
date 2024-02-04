package com.example.spring.domain.qna.dto;

import com.example.spring.domain.qna.enums.AnswerStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public class QnaRequestDTO {
    @Getter
    public static class QnaSaveDto {
        @NotNull
        Long memberId;
        @NotBlank
        String title;
        @NotBlank
        String body;
    }


}
