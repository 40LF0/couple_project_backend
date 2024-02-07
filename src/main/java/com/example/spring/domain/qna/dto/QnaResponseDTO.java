package com.example.spring.domain.qna.dto;

import com.example.spring.domain.qna.enums.AnswerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class QnaResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QnaEntityDto {
        Long qnaId;
        Long memberId;
        String title;
        String body;
        String answer;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QnaPreviewListDTO {
        Long qnaId;
        String title;
        AnswerStatus answerStatus;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QnaAdminListDTO {
        Long qnaId;
        String title;
        String body;
    }


}
