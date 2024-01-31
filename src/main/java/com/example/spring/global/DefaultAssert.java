package com.example.spring.global;

import com.example.spring.global.apiResponse.code.status.ErrorStatus;
import com.example.spring.global.apiResponse.exception.GeneralException;
import org.springframework.util.Assert;


public class DefaultAssert extends Assert {

    public static void isTrue(boolean value){
        if(!value){
            throw new GeneralException(ErrorStatus.INVALID_USER);
        }
    }

    public static void isTrue(boolean value, String message){
        if(!value){
            throw new GeneralException(ErrorStatus.INVALID_USER);
        }
    }
}