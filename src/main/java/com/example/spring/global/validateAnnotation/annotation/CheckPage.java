package com.example.spring.global.validateAnnotation.annotation;

import com.example.spring.global.validateAnnotation.validator.CheckPageValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CheckPageValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPage {
    String message() default "올바르지 않은 페이지 번호 입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
