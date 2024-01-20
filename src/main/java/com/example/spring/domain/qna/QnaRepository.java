package com.example.spring.domain.qna;

import com.example.spring.domain.qna.domain.Qna;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QnaRepository extends JpaRepository<Qna, Long> {
}
