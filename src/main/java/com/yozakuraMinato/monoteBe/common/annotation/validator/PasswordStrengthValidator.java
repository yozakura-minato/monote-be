package com.yozakuraMinato.monoteBe.common.annotation.validator;

import com.yozakuraMinato.monoteBe.common.annotation.IsStrongPassword;
import com.yozakuraMinato.monoteBe.common.shared.CommonConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordStrengthValidator implements ConstraintValidator<IsStrongPassword, String> {

    private final Pattern UPPER_PATTERN = Pattern.compile(CommonConstraint.Regex.UPPER_REGEX);
    private final Pattern LOWER_PATTERN = Pattern.compile(CommonConstraint.Regex.LOWER_REGEX);
    private final Pattern DIGIT_PATTERN = Pattern.compile(CommonConstraint.Regex.DIGIT_REGEX);
    private final Pattern SPECIAL_PATTERN = Pattern.compile(CommonConstraint.Regex.SPECIAL_REGEX);
    private int minimalSize;
    private int minimalLevel;

    @Override
    public void initialize(IsStrongPassword constraintAnnotation) {
        this.minimalSize = constraintAnnotation.minimalSize();
        this.minimalLevel = constraintAnnotation.minimalLevel();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password == null) return true;

        if (password.length() < minimalSize) return false;

        int level = 0;
        if (UPPER_PATTERN.matcher(password).find()) level++;
        if (LOWER_PATTERN.matcher(password).find()) level++;
        if (DIGIT_PATTERN.matcher(password).find()) level++;
        if (SPECIAL_PATTERN.matcher(password).find()) level++;
        return level >= minimalLevel;
    }

}
