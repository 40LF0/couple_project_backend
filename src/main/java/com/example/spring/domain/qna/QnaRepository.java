package com.example.spring.domain.qna;

import com.example.spring.domain.member.domain.Member;
import com.example.spring.domain.qna.domain.Qna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QnaRepository extends JpaRepository<Qna, Long> {
    Page<Qna> findAllByMember(Member member, Pageable pageable);
}
