package com.yozakuraMinato.monoteBe.common.exception.handler;

import com.yozakuraMinato.monoteBe.common.dto.ApplicationResponse;
import com.yozakuraMinato.monoteBe.common.exception.BusinessRuleException;
import com.yozakuraMinato.monoteBe.common.exception.ResourceConflictException;
import com.yozakuraMinato.monoteBe.common.exception.ResourceNotFoundException;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@ControllerAdvice
public class GeneralExceptionHandler {

    private final String BAD_REQUEST = "general.badRequest";
    private final String UNAUTHORIZED = "general.unauthorized";
    private final String INTERNAL_SERVER_ERROR = "general.internalServerError";

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApplicationResponse<?>> handleResourceNotFoundException(
            ResourceNotFoundException resourceNotFoundException
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApplicationResponse.error(resourceNotFoundException.getMessage()));
    }

    @ExceptionHandler(value = ResourceConflictException.class)
    public ResponseEntity<ApplicationResponse<?>> handleResourceConflictException(
            ResourceConflictException resourceConflictException
    ) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApplicationResponse.error(resourceConflictException.getMessage()));
    }

    @ExceptionHandler(value = BusinessRuleException.class)
    public ResponseEntity<ApplicationResponse<?>> handleBusinessRuleException(
            BusinessRuleException businessRuleException
    ) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(ApplicationResponse.error(businessRuleException.getMessage()));
    }

    @ExceptionHandler(value = HandlerMethodValidationException.class)
    public ResponseEntity<ApplicationResponse<?>> handleHandlerMethodValidationException(
            HandlerMethodValidationException handlerMethodValidationException
    ) {
        String message = handlerMethodValidationException.getAllErrors().getFirst().getDefaultMessage();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApplicationResponse.error(
                        message == null || message.isBlank() ? BAD_REQUEST : message
                ));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApplicationResponse<?>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException httpMessageNotReadableException
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApplicationResponse.error(BAD_REQUEST));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApplicationResponse<?>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException
    ) {
        List<FieldError> errors = methodArgumentNotValidException.getFieldErrors();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApplicationResponse.error(
                        errors.isEmpty() ? BAD_REQUEST : errors.getFirst().getDefaultMessage()
                ));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApplicationResponse<?>> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException methodArgumentTypeMismatchException
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApplicationResponse.error(BAD_REQUEST));
    }

    @ExceptionHandler(BeanInstantiationException.class)
    public ResponseEntity<ApplicationResponse<?>> handleBeanInstantiationException (
            BeanInstantiationException beanInstantiationException
    ) {
        Throwable rootCause = beanInstantiationException.getMostSpecificCause();

        if (rootCause instanceof IllegalArgumentException) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApplicationResponse.error(rootCause.getMessage())
            );
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApplicationResponse.error(INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ApplicationResponse<?>> handleBadCredentialsException (
            BadCredentialsException badCredentialsException
    ) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApplicationResponse.error(UNAUTHORIZED));
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApplicationResponse<?>> handleRuntimeException(
            RuntimeException runtimeException
    ) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApplicationResponse.error(INTERNAL_SERVER_ERROR));
    }

}
