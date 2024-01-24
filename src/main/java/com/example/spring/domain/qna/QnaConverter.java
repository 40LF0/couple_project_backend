package com.example.spring.domain.qna;
import com.example.spring.domain.member.MemberService;
import com.example.spring.domain.member.domain.Member;
import com.example.spring.domain.qna.domain.Qna;
import com.example.spring.domain.qna.dto.QnaRequestDTO;
import com.example.spring.domain.qna.dto.QnaResponseDTO;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

public class QnaConverter {
    public static MemberService memberService;

    public static Qna toQnaEntity(QnaRequestDTO.QnaSaveDto request){
        Member member = memberService.findById(request.getMemberId());
        return Qna.builder()
                .member(member)
                .title(request.getTitle())
                .body(request.getBody())
                .build();
    }

    public static QnaResponseDTO.QnaEntityDto toQnaEntityDto(Qna qna){
    // 엔티티 -> DTO
    // qna 엔티티에서 받아온 정보(qnaId 이용 - service) + qnaId + answerStatus => DTO
        return QnaResponseDTO.QnaEntityDto.builder()
                .qnaID(qna.getQnaId())
                .title(qna.getTitle())
                .body(qna.getBody())
                .build();
    }

}
