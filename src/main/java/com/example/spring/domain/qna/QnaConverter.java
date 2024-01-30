package com.example.spring.domain.qna;
import com.example.spring.domain.member.MemberService;
import com.example.spring.domain.member.domain.Member;
import com.example.spring.domain.qna.domain.Qna;
import com.example.spring.domain.qna.dto.QnaRequestDTO;
import com.example.spring.domain.qna.dto.QnaResponseDTO;
import com.example.spring.domain.qna.enums.AnswerStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import static com.example.spring.domain.qna.enums.AnswerStatus.WAITING;

@Component
@RequiredArgsConstructor
public class QnaConverter {
    private final MemberService memberService;

    public Qna toQnaEntity(QnaRequestDTO.QnaSaveDto request){
        Member member = memberService.findById(request.getMemberId());
        return Qna.builder()
                .member(member)
                .title(request.getTitle())
                .body(request.getBody())
                .build();
    }

    public QnaResponseDTO.QnaEntityDto toQnaEntityDto(Qna qna){
        // 답변 받으면 바뀌는 로직 필요
        AnswerStatus answerStatus = WAITING;
        return QnaResponseDTO.QnaEntityDto.builder()
                .qnaId(qna.getQnaId())
                .memberId(qna.getMember().getMemberId())
                .title(qna.getTitle())
                .body(qna.getBody())
                .answerStatus(answerStatus)
                .build();
    }

    public Page<QnaResponseDTO.QnaPreviewListDTO> toMyQnaPreviewListDto(Page<Qna> qnas) {
        // 운영자가 답변 완료할 시 바뀌는 로직 필요
        AnswerStatus answerStatus = WAITING;

        return qnas.map(qna -> {
            return QnaResponseDTO.QnaPreviewListDTO.builder()
                    .qnaId(qna.getQnaId())
                    .title(qna.getTitle())
                    .answerStatus(answerStatus)
                    .build();
        });
    }

}
