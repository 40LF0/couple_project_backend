package com.example.spring.domain.qna;

import com.example.spring.domain.qna.domain.Qna;
import com.example.spring.domain.qna.dto.QnaRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QnaService {
    private final QnaRepository qnaRepository;
    @Transactional
    public Qna createQna(QnaRequestDTO.QnaSaveDto request){
        Qna qna = QnaConverter.toQnaEntity(request);
        qnaRepository.save(qna);
        return qna;
    }
}
