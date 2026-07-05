package com.yozakuraMinato.monoteBe.common.annotation.validator;

import com.yozakuraMinato.monoteBe.common.annotation.HasValidPageSize;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class PageableSizeValidator implements ConstraintValidator<HasValidPageSize, Pageable> {

    private Set<Integer> allowedSizes;
    private String message;

    @Override
    public void initialize(HasValidPageSize constraintAnnotation) {
        this.allowedSizes = Arrays
                .stream(constraintAnnotation.allowedSizes())
                .boxed()
                .collect(Collectors.toUnmodifiableSet());
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Pageable pageable, ConstraintValidatorContext constraintValidatorContext) {
        if (pageable == null) return true;

        int pageSize = pageable.getPageSize();
        boolean isValid = allowedSizes.contains(pageSize);

        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }

        return isValid;
    }

}
