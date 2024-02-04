package com.example.spring.domain.qna;

import com.example.spring.domain.qna.domain.Qna;
import com.example.spring.domain.qna.dto.QnaRequestDTO;
import com.example.spring.domain.qna.dto.QnaResponseDTO;
import com.example.spring.global.apiResponse.ApiResponse;
import com.example.spring.domain.qna.QnaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/qnas")
public class QnaController {
    private final QnaService qnaService;

    @PostMapping("")
    public ApiResponse<QnaResponseDTO.QnaEntityDto> join(@RequestBody @Valid QnaRequestDTO.QnaSaveDto request){
        QnaResponseDTO.QnaEntityDto qnaDTO = qnaService.createQna(request);
        return ApiResponse.onSuccess(qnaDTO);
    }

    @GetMapping("")
    public ApiResponse<QnaResponseDTO.QnaEntityDto> getQna(@RequestParam Long qnaId){
        return ApiResponse.onSuccess(qnaService.getQnaInfo(qnaId));
    }

    @GetMapping("/previews")
    public ApiResponse<Page<QnaResponseDTO.QnaPreviewListDTO>> getPreviewList(@RequestParam Long memberId, Pageable pageable){
        Page<QnaResponseDTO.QnaPreviewListDTO> previews = qnaService.getMyQnaPreviewList(memberId, pageable);
        return ApiResponse.onSuccess(previews);
    }

    @GetMapping("/admin")
    public ApiResponse<Page<QnaResponseDTO.QnaAdminListDTO>> getQnaAdminList(Pageable pageable){
        Page<QnaResponseDTO.QnaAdminListDTO> qnaLists = qnaService.getQnaWaitingList(pageable);
        return ApiResponse.onSuccess(qnaLists);
    }

}
