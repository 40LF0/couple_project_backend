package com.example.spring.domain.qna;

import com.example.spring.domain.qna.domain.Qna;
import com.example.spring.domain.qna.dto.QnaRequestDTO;
import com.example.spring.global.apiResponse.ApiResponse;
import com.example.spring.domain.qna.QnaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/qnas")
public class QnaController {
    private final QnaService qnaService;

    @PostMapping("/")
    public ApiResponse<Boolean> join(@RequestBody @Valid QnaRequestDTO.QnaSaveDto request){
        Qna qna = qnaService.createQna(request);
        return ApiResponse.onSuccess(Boolean.TRUE);
    }
}
