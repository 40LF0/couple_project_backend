package com.example.spring.domain.qna;

import com.example.spring.domain.qna.domain.Qna;
import com.example.spring.domain.qna.dto.QnaRequestDTO;

public class QnaConverter {
    public static Qna toQnaEntity(QnaRequestDTO.QnaSaveDto request){
        return Qna.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .build();
    }
}
