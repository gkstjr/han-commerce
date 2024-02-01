package com.toy.hancommerce.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.hancommerce.error.ErrorCode;
import com.toy.hancommerce.error.ExceptionDto;
import com.toy.hancommerce.error.MyException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Slf4j
@Component // 유효한 자격증명을 제공하지 않고 접근하려 할때 401 Unauthorized 에러를 리턴하는 class
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException , MyException {
        //유효한 자격증명을 제공하지 않고 접근하려 할 때 401 응답
        log.info("======================Security - JwtAuthenticationEntryPoint-commence=====================");
//        throw new MyException(ErrorCode.UNAUTHENTICATED);
//        시큐리티 특성상 filter 단에서 예외를 뱉어내기 때문에 내가 원하는 ExceptionManager Controller에 닿지 못함 -> 직접 ExceptionManager 응답형태처럼 응답처리
          log.error("JwtAuthenticationEntryPoint 에러 ",authException);
          response.setStatus(HttpStatus.UNAUTHORIZED.value());
          response.setContentType("application/json");
          response.setCharacterEncoding("UTF-8");
          try {
              String json = new ObjectMapper().writeValueAsString(new ExceptionDto(ErrorCode.UNAUTHENTICATED));
              response.getWriter().write(json);
          } catch (Exception e) {
              log.error(e.getMessage());
          }
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
