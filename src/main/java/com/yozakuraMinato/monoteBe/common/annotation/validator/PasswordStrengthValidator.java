package com.yozakuraMinato.monoteBe.common.annotation.validator;

import com.yozakuraMinato.monoteBe.common.annotation.IsStrongPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordStrengthValidator implements ConstraintValidator<IsStrongPassword, String> {

    private final Pattern UPPER = Pattern.compile("[A-Z]");
    private final Pattern LOWER = Pattern.compile("[a-z]");
    private final Pattern DIGIT = Pattern.compile("[0-9]");
    private final Pattern SPECIAL = Pattern.compile("[^A-Za-z0-9]");
    private int minimalSize;
    private int minimallevel;

    @Override
    public void initialize(IsStrongPassword constraintAnnotation) {
        this.minimalSize = constraintAnnotation.minimalSize();
        this.minimallevel = constraintAnnotation.minimalLevel();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password == null) return true;

        if (password.length() < minimalSize) return false;

        int level = 0;
        if (UPPER.matcher(password).find()) level++;
        if (LOWER.matcher(password).find()) level++;
        if (DIGIT.matcher(password).find()) level++;
        if (SPECIAL.matcher(password).find()) level++;
        return level >= minimallevel;
    }

}
