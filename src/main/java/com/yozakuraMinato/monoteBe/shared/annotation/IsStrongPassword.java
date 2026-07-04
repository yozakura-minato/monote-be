package com.yozakuraMinato.monoteBe.shared.annotation;

import com.yozakuraMinato.monoteBe.shared.annotation.validator.PasswordStrengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsStrongPassword {
    String message() default "Password is weak";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
