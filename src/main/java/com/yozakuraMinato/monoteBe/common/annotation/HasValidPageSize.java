package com.yozakuraMinato.monoteBe.common.annotation;

import com.yozakuraMinato.monoteBe.common.annotation.validator.PageableSizeValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PageableSizeValidator.class)
@Documented
public @interface HasValidPageSize {

    String message() default "Invalid pageable";
    int[] allowedSizes() default { 10, 20, 50, 100 };

}
