package com.yozakuraMinato.monoteBe.shared.annotation.validator;

import com.yozakuraMinato.monoteBe.shared.annotation.IsStrongPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordStrengthValidator implements ConstraintValidator<IsStrongPassword, String> {

    private static final Pattern UPPER = Pattern.compile("[A-Z]");
    private static final Pattern LOWER = Pattern.compile("[a-z]");
    private static final Pattern DIGIT = Pattern.compile("[0-9]");
    private static final Pattern SPECIAL = Pattern.compile("[^A-Za-z0-9]");

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password == null) return false;

        int typeCount = 0;
        if (UPPER.matcher(password).find()) typeCount++;

        if (LOWER.matcher(password).find()) typeCount++;
        if (typeCount >= 2) return true;

        if (DIGIT.matcher(password).find()) typeCount++;
        if (typeCount >= 2) return true;

        if (SPECIAL.matcher(password).find()) typeCount++;
        return typeCount >= 2;
    }

}
