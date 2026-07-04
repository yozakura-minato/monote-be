package com.yozakuraMinato.monoteBe.common.exception;

import com.yozakuraMinato.monoteBe.common.exception.custom.ResourceConflictException;
import com.yozakuraMinato.monoteBe.common.exception.custom.ResourceNotFoundException;
import com.yozakuraMinato.monoteBe.common.exception.custom.BusinessRuleException;
import com.yozakuraMinato.monoteBe.common.wrapper.ApplicationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApplicationResponse<?>> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApplicationResponse.error(resourceNotFoundException.getMessage()));
    }

    @ExceptionHandler(value = ResourceConflictException.class)
    public ResponseEntity<ApplicationResponse<?>> handleResourceConflictException(ResourceConflictException resourceConflictException) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApplicationResponse.error(resourceConflictException.getMessage()));
    }

    @ExceptionHandler(value = BusinessRuleException.class)
    public ResponseEntity<ApplicationResponse<?>> handleBusinessRuleException(BusinessRuleException businessRuleException) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(ApplicationResponse.error(businessRuleException.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApplicationResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<FieldError> errors = methodArgumentNotValidException.getFieldErrors();
        if(errors.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApplicationResponse.error(GeneralMessage.badRequest));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApplicationResponse.error(errors.getFirst().getDefaultMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApplicationResponse<?>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApplicationResponse.error(GeneralMessage.badRequest));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApplicationResponse<?>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApplicationResponse.error(GeneralMessage.badRequest));
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApplicationResponse<?>> handleRuntimeException(RuntimeException runtimeException) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApplicationResponse.error(GeneralMessage.internalError));
    }

}
