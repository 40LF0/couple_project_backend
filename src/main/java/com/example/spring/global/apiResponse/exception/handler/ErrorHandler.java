package com.example.spring.global.apiResponse.exception.handler;


import com.example.spring.global.apiResponse.code.BaseErrorCode;
import com.example.spring.global.apiResponse.exception.GeneralException;

public class ErrorHandler extends GeneralException {

    public ErrorHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
