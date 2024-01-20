package com.example.spring.global;

import com.example.spring.domain.member.exception.InvalidUserException;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

public class DefaultAssert extends Assert {

    public static void isTrue(boolean value){
        if(!value){
            throw new InvalidUserException();
        }
    }

    public static void isTrue(boolean value, String message){
        if(!value){
            throw new InvalidUserException();
        }
    }
}