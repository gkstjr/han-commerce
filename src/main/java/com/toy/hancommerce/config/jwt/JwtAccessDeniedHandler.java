package com.toy.hancommerce.config.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
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

        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
}
