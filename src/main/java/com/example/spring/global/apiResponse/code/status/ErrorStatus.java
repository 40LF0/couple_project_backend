package com.example.spring.global.apiResponse.code.status;

import com.example.spring.global.apiResponse.code.BaseErrorCode;
import com.example.spring.global.apiResponse.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // 멤버 관련 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),
    ALREADY_EXIST_EMAIL(HttpStatus.BAD_REQUEST, "MEMBER4003", "이미 존재하는 이메일 입니다."),
    INVALID_USER(HttpStatus.BAD_REQUEST, "MEMBER4004", "유효하지 않은 사용자 입니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "MEMBER4005", "유효하지 않은 리프레시 토큰 입니다."),
    // 예시,,,
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),

    PAGE_FORMAT_BAD_REQUEST(HttpStatus.BAD_REQUEST,"PAGE4001","잘못된 페이지 번호 형식입니다"),

    // Ror test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),
    // Region Error
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4001", "가게가 없습니다."),
    // Region Error
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "REGION4001", "지역이 없습니다."),
    // Category Error
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "CATEGORY4001", "카테고리가 없습니다."),

    // 리뷰 관련 에러
    REVIEW_IMAGE_QUANTITY_ERROR(HttpStatus.BAD_REQUEST, "REVIEW4001", "리뷰에 첨부된 사진이 없거나 너무 많습니다."),
    REVIEW_SPOT_QUANTITY_ERROR(HttpStatus.BAD_REQUEST, "REVIEW4002", "리뷰 장소가 없거나 너무 많습니다."),
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW4002", "후기가 없습니다"),

    // 문의 관련 에러
    QNA_NOT_FOUND(HttpStatus.NOT_FOUND, "QNA4001","문의가 없습니다");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}