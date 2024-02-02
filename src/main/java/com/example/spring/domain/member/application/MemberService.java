package com.example.spring.domain.member.application;

import com.example.spring.domain.member.domain.Member;
import com.example.spring.domain.member.domain.repository.MemberRepository;
import com.example.spring.domain.member.dto.MemberDetailRes;
import com.example.spring.global.apiResponse.code.status.ErrorStatus;
import com.example.spring.global.apiResponse.exception.GeneralException;
import com.example.spring.global.config.security.UserPrincipal;
import com.example.spring.global.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findById(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(() ->
                new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
    }

    public Member findByEmail(String email){
        return memberRepository.findByEmail(email).orElseThrow(() ->
                new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
    }

    public MemberDetailRes getCurrentUser(){
        Member member = findByEmail(SecurityUtil.getCurrentMemberId());
        return MemberDetailRes.toDto(member);
    }


    // 만약, member 엔티티에 제약 조건을 추가한다면, 아래 더미 함수에도 해당 내용 반영해 주세요!
    @Transactional
    public Long saveAndGetDummyID(){
        Member member = Member.builder()
                .build();
        memberRepository.save(member);
        return member.getMemberId();
    }
}
