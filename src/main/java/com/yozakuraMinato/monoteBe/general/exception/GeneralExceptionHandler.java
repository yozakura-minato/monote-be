package com.yozakuraMinato.monoteBe.general.exception;

import com.yozakuraMinato.monoteBe.general.model.ResponseBody;
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
    public ResponseEntity<ResponseBody<?>> handleAccessDeniedException(AccessDeniedException accessDeniedException) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ResponseBody.error(GeneralMessage.accessDeniedException));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseBody<?>> handleBadCredentialsException(BadCredentialsException badCredentialsException) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ResponseBody.error(GeneralMessage.badCredentialsException));
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ResponseBody<?>> handleDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ResponseBody.error(GeneralMessage.dataIntegrityViolationException));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseBody<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<FieldError> errors = methodArgumentNotValidException.getFieldErrors();
        if(errors.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ResponseBody.error(GeneralMessage.methodArgumentNotValidException));
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseBody.error(errors.getFirst().getDefaultMessage()));
    }

    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<ResponseBody<?>> handleResponseStatusException(ResponseStatusException responseStatusException) {
        if(responseStatusException.getMessage().isBlank()) {
            return ResponseEntity
                    .status(responseStatusException.getStatusCode())
                    .body(ResponseBody.error(GeneralMessage.responseStatusException));
        }
        return ResponseEntity
                .status(responseStatusException.getStatusCode())
                .body(ResponseBody.error(responseStatusException.getReason()));
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ResponseBody<?>> handleRuntimeException(RuntimeException runtimeException) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseBody.error(GeneralMessage.runtimeException));
    }

}
