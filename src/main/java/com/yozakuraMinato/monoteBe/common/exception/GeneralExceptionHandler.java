package com.yozakuraMinato.monoteBe.common.exception;

import com.yozakuraMinato.monoteBe.common.wrapper.ApplicationResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApplicationResponse<?>> handleAccessDeniedException(AccessDeniedException accessDeniedException) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApplicationResponse.error(GeneralMessage.accessDeniedException));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApplicationResponse<?>> handleBadCredentialsException(BadCredentialsException badCredentialsException) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApplicationResponse.error(GeneralMessage.badCredentialsException));
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ApplicationResponse<?>> handleDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApplicationResponse.error(GeneralMessage.dataIntegrityViolationException));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApplicationResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<FieldError> errors = methodArgumentNotValidException.getFieldErrors();
        if(errors.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApplicationResponse.error(GeneralMessage.methodArgumentNotValidException));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApplicationResponse.error(errors.getFirst().getDefaultMessage()));
    }

    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<ApplicationResponse<?>> handleResponseStatusException(ResponseStatusException responseStatusException) {
        if(responseStatusException.getMessage().isBlank()) {
            return ResponseEntity
                    .status(responseStatusException.getStatusCode())
                    .body(ApplicationResponse.error(GeneralMessage.responseStatusException));
        }
        return ResponseEntity
                .status(responseStatusException.getStatusCode())
                .body(ApplicationResponse.error(responseStatusException.getReason()));
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApplicationResponse<?>> handleRuntimeException(RuntimeException runtimeException) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApplicationResponse.error(GeneralMessage.runtimeException));
    }

}
