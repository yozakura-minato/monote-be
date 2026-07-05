package com.yozakuraMinato.monoteBe.common.annotation;

import com.yozakuraMinato.monoteBe.common.annotation.validator.PasswordStrengthValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsStrongPassword {

    String message() default "Password is weak";
    int minimalSize() default 0;
    int minimalLevel() default 0;

}
