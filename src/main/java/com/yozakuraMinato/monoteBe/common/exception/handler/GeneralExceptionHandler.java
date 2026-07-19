package com.yozakuraMinato.monoteBe.common.exception.handler;

import com.yozakuraMinato.monoteBe.common.exception.BusinessRuleException;
import com.yozakuraMinato.monoteBe.common.exception.ResourceConflictException;
import com.yozakuraMinato.monoteBe.common.exception.ResourceNotFoundException;
import com.yozakuraMinato.monoteBe.common.payload.ApiResponse;
import com.yozakuraMinato.monoteBe.common.shared.CommonMessage;
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

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(
            ResourceNotFoundException resourceNotFoundException
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(resourceNotFoundException.getMessage()));
    }

    @ExceptionHandler(value = ResourceConflictException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceConflictException(
            ResourceConflictException resourceConflictException
    ) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(resourceConflictException.getMessage()));
    }

    @ExceptionHandler(value = BusinessRuleException.class)
    public ResponseEntity<ApiResponse<?>> handleBusinessRuleException(
            BusinessRuleException businessRuleException
    ) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(ApiResponse.error(businessRuleException.getMessage()));
    }

    @ExceptionHandler(value = HandlerMethodValidationException.class)
    public ResponseEntity<ApiResponse<?>> handleHandlerMethodValidationException(
            HandlerMethodValidationException handlerMethodValidationException
    ) {
        String message = handlerMethodValidationException.getAllErrors().getFirst().getDefaultMessage();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(
                        message == null || message.isBlank() ? CommonMessage.BAD_REQUEST : message
                ));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException httpMessageNotReadableException
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(CommonMessage.BAD_REQUEST));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException
    ) {
        List<FieldError> errors = methodArgumentNotValidException.getFieldErrors();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(
                        errors.isEmpty() ? CommonMessage.BAD_REQUEST : errors.getFirst().getDefaultMessage()
                ));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException methodArgumentTypeMismatchException
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(CommonMessage.BAD_REQUEST));
    }

    @ExceptionHandler(BeanInstantiationException.class)
    public ResponseEntity<ApiResponse<?>> handleBeanInstantiationException (
            BeanInstantiationException beanInstantiationException
    ) {
        Throwable rootCause = beanInstantiationException.getMostSpecificCause();

        if (rootCause instanceof IllegalArgumentException) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(rootCause.getMessage())
            );
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(CommonMessage.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ApiResponse<?>> handleBadCredentialsException (
            BadCredentialsException badCredentialsException
    ) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error(CommonMessage.UNAUTHORIZED));
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeException(
            RuntimeException runtimeException
    ) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(CommonMessage.INTERNAL_SERVER_ERROR));
    }

}
