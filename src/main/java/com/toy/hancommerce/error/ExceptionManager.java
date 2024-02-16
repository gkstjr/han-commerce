package com.toy.hancommerce.error;

import jakarta.validation.UnexpectedTypeException;
import jakarta.xml.bind.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.UnexpectedException;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class ExceptionManager {

    @ExceptionHandler(MyException.class)
    public ResponseEntity<?> myExceptionHandler(MyException e) {
          log.error("myExceptionHandler 발생" ,e);
          return ResponseEntity.status(e.getErrorCode().getStatus())
                  .body(new ExceptionDto(e.getErrorCode()));
    }
    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<?> UnexpectedTypeExceptionHandler(UnexpectedTypeException e) {
        log.error("UnexpectedTypeExceptionHandler 발생" ,e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionDto(e.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validExceptionHandler(MethodArgumentNotValidException e) {
        log.error("validExceptionHandler 발생" ,e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionDto(Objects.requireNonNull(e.getBindingResult().getFieldError(), "유효성 검증 실패(원인 x)")
                                                                                  .getDefaultMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("httpMessageNotReadableException 발생" ,e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionDto(e.getMessage()));
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> AuthenticationException(AuthenticationException e) {
        log.error("AuthenticationException 발생" ,e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionDto(e.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeException(RuntimeException e) {
        log.error("RuntimeException 발생" ,e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionDto(e.getMessage()));
    }
}
