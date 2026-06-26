package com.yozakuraMinato.monoteBe.security.annotation.validator;

import com.yozakuraMinato.monoteBe.security.annotation.IsStrongPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordStrengthValidator implements ConstraintValidator<IsStrongPassword, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;

        int typeCount = 0;
        typeCount += value.matches(".*[A-Z].*") ? 1 : 0;
        typeCount += value.matches(".*[a-z].*") ? 1 : 0;
        typeCount += value.matches(".*[0-9].*") ? 1 : 0;
        typeCount += value.matches(".*[^A-Za-z0-9].*") ? 1 : 0;
        return typeCount >= 2;
    }

}
