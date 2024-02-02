package com.example.spring.domain.qna;

import com.example.spring.domain.member.domain.Member;
import com.example.spring.domain.qna.domain.Qna;
import com.example.spring.domain.qna.enums.AnswerStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QnaRepository extends JpaRepository<Qna, Long> {
    Page<Qna> findAllByMember(Member member, Pageable pageable);
    Page<Qna> findAllByAnswerStatus(AnswerStatus answerStatus, Pageable pageable);
}
