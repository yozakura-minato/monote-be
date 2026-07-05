package com.yozakuraMinato.monoteBe.common.annotation;

import com.yozakuraMinato.monoteBe.common.annotation.validator.PageableSizeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PageableSizeValidator.class)
@Documented
public @interface HasValidPageSize {
    String message() default "Invalid pageable";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int[] allowedSizes() default { 10, 20, 50, 100 };
}
