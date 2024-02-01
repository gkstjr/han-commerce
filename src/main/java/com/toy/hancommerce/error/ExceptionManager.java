package com.toy.hancommerce.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(MyException.class)
    public ResponseEntity<?> myExceptionHandler(MyException e) {
        e.printStackTrace();
          return ResponseEntity.status(e.getErrorCode().getStatus())
                  .body(new ExceptionDto(e.getErrorCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> ValidExceptionHandler(MethodArgumentNotValidException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionDto(Objects.requireNonNull(e.getBindingResult().getFieldError(), "유효성 검증 실패(원인 x)")
                                                                                  .getDefaultMessage()));
    }

}
