package com.example.spring.domain.qna;

import com.example.spring.domain.member.MemberService;
import com.example.spring.domain.member.domain.Member;
import com.example.spring.domain.qna.domain.Qna;
import com.example.spring.domain.qna.dto.QnaRequestDTO;
import com.example.spring.domain.qna.dto.QnaResponseDTO;
import com.example.spring.domain.qna.enums.AnswerStatus;
import com.example.spring.global.apiResponse.code.status.ErrorStatus;
import com.example.spring.global.apiResponse.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QnaService {
    private final QnaRepository qnaRepository;
    private final MemberService memberService;
    private final QnaConverter qnaConverter;
    @Transactional
    public QnaResponseDTO.QnaEntityDto createQna(QnaRequestDTO.QnaSaveDto request){
        Qna qna = qnaConverter.toQnaEntity(request);
        qnaRepository.save(qna);
        return qnaConverter.toQnaEntityDto(qna);
    }

    public QnaResponseDTO.QnaEntityDto getQnaInfo(Long qnaId) {
        Qna qna = findById(qnaId);
        return qnaConverter.toQnaEntityDto(qna);
    }

    public Qna findById(Long qnaId){
        return qnaRepository.findById(qnaId).orElseThrow(()->new GeneralException(ErrorStatus.QNA_NOT_FOUND));
    }

    public Page<QnaResponseDTO.QnaPreviewListDTO> getMyQnaPreviewList(Long memberId, Pageable pageable){
        Member member = memberService.findById(memberId);
        Page <Qna> qnas = qnaRepository.findAllByMember(member, pageable);
        return qnaConverter.toMyQnaPreviewListDto(qnas);
    }

    public Page<QnaResponseDTO.QnaAdminListDTO> getQnaWaitingList(Pageable pageable) {
        Page <Qna> qnas = qnaRepository.findAllByAnswerStatus(AnswerStatus.WAITING, pageable);
        return qnaConverter.toQnaAdminListDto(qnas);
    }

    public Qna createQnaAnswer(QnaRequestDTO.QnaAnswerDto request, Long qnaId) {
        Qna qna = findById(qnaId);
        qna.updateAnswer(request.getAnswer());
        qna.updateAnswerStatus();
        return qna;
    }


}
