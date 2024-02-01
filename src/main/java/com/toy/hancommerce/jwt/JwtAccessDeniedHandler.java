package com.toy.hancommerce.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.hancommerce.error.ErrorCode;
import com.toy.hancommerce.error.ExceptionDto;
import com.toy.hancommerce.error.MyException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Slf4j
@Component //필요한 권한이 존재하지 않는 경우에 403 Forbidden 에러를 리턴하는 class
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("======================Security - JwtAccessDeniedHandler-handle=====================");
//        시큐리티 특성상 filter 단에서 예외를 뱉어내기 때문에 내가 원하는 ExceptionManager Controller에 닿지 못함 -> 직접 ExceptionManager 응답형태처럼 응답처리
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String json = new ObjectMapper().writeValueAsString(new ExceptionDto(ErrorCode.UNAUTHORIZED));
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
//        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
}
