package com.example.spring.domain.qna;

import com.example.spring.domain.qna.domain.Qna;
import com.example.spring.domain.qna.dto.QnaRequestDTO;
import com.example.spring.domain.qna.dto.QnaResponseDTO;
import com.example.spring.global.apiResponse.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/qnas")
public class QnaController {
    private final QnaService qnaService;
    private final QnaConverter qnaConverter;

    @PostMapping("")
    public ApiResponse<QnaResponseDTO.QnaEntityDto> postQna(@RequestBody @Valid QnaRequestDTO.QnaSaveDto request){
        QnaResponseDTO.QnaEntityDto qnaDTO = qnaService.createQna(request);
        return ApiResponse.onSuccess(qnaDTO);
    }

    @GetMapping("/{qnaId}")
    public ApiResponse<QnaResponseDTO.QnaEntityDto> getQna(@PathVariable Long qnaId){
        return ApiResponse.onSuccess(qnaService.getQnaInfo(qnaId));
    }

    @GetMapping("/{memberId}/previews")
    public ApiResponse<Page<QnaResponseDTO.QnaPreviewListDTO>> getPreviewList(@PathVariable Long memberId, Pageable pageable){
        Page<QnaResponseDTO.QnaPreviewListDTO> previews = qnaService.getMyQnaPreviewList(memberId, pageable);
        return ApiResponse.onSuccess(previews);
    }

    @GetMapping("")
    public ApiResponse<Page<QnaResponseDTO.QnaAdminListDTO>> getQnaAdminList(Pageable pageable){
        Page<QnaResponseDTO.QnaAdminListDTO> qnaLists = qnaService.getQnaWaitingList(pageable);
        return ApiResponse.onSuccess(qnaLists);
    }

    @PutMapping("")
    public ApiResponse<QnaResponseDTO.QnaEntityDto> putQnaAnswer(@RequestBody @Valid QnaRequestDTO.QnaAnswerDto request) {
        Qna qna = qnaService.createQnaAnswer(request);
        return ApiResponse.onSuccess(qnaConverter.toQnaEntityDto(qna));
    }

}
