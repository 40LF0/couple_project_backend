package com.example.spring.domain.qna;

import com.example.spring.domain.member.application.MemberService;
import com.example.spring.domain.member.domain.Member;
import com.example.spring.domain.qna.domain.Qna;
import com.example.spring.domain.qna.dto.QnaRequestDTO;
import com.example.spring.domain.qna.dto.QnaResponseDTO;
import com.example.spring.domain.qna.enums.AnswerStatus;
import com.example.spring.global.apiResponse.code.status.ErrorStatus;
import com.example.spring.global.apiResponse.exception.GeneralException;
import com.example.spring.global.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.ldap.ManageReferralControl;
import java.util.Objects;


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
        Member securityMember = memberService.findByEmail(SecurityUtil.getCurrentMemberId());
        if(!Objects.equals(qna.getMember().getMemberId(), securityMember.getMemberId())){
            throw new GeneralException(ErrorStatus.QNA_NOT_FOUND);
        }
        return qnaConverter.toQnaEntityDto(qna);
    }

    public Qna findById(Long qnaId){
        return qnaRepository.findById(qnaId).orElseThrow(()->new GeneralException(ErrorStatus.QNA_NOT_FOUND));
    }

    public Page<QnaResponseDTO.QnaPreviewListDTO> getMyQnaPreviewList(Pageable pageable){
        Member member = memberService.findByEmail(SecurityUtil.getCurrentMemberId());
        Page <Qna> qnas = qnaRepository.findAllByMember(member, pageable);
        return qnaConverter.toMyQnaPreviewListDto(qnas);
    }

    public Page<QnaResponseDTO.QnaAdminListDTO> getQnaWaitingList(Pageable pageable) {
        Member securityMember = memberService.findByEmail(SecurityUtil.getCurrentMemberId());

        if (!Objects.equals(securityMember.getRole(),"MANAGER")){
            throw new GeneralException(ErrorStatus._FORBIDDEN);
        }

        Page <Qna> qnas = qnaRepository.findAllByAnswerStatus(AnswerStatus.WAITING, pageable);
        return qnaConverter.toQnaAdminListDto(qnas);
    }

    @Transactional
    public Qna createQnaAnswer(QnaRequestDTO.QnaAnswerDto request) {

        Member securityMember = memberService.findByEmail(SecurityUtil.getCurrentMemberId());
        if(!Objects.equals(securityMember.getRole(),"MANAGER")){
            throw new GeneralException(ErrorStatus._FORBIDDEN);
        }

        Qna qna = findById(request.getQnaId());
        qna.updateAnswer(request.getAnswer());
        qna.updateAnswerStatus();
        return qna;
    }


}
