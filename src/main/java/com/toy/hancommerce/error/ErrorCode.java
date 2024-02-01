package com.toy.hancommerce.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND,"유저를 찾을 수 없습니다."),
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "유저명이 중복됩니다"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST,"유효성 검증 실패"),

    //security 필터 단에서 뱉어내느 예외 코드
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"권한 없음"),
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED, "인증 실패");

    private HttpStatus status;
    private String message;

}
