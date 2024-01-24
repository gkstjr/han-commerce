//package com.toy.hancommerce.config;
//
//import com.toy.hancommerce.config.jwt.JwtAccessDeniedHandler;
//import com.toy.hancommerce.config.jwt.JwtAuthenticationEntryPoint;
//import com.toy.hancommerce.config.jwt.JwtSecurityConfig;
//import com.toy.hancommerce.config.jwt.TokenProvider;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final TokenProvider tokenProvider;
//    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
//
//    //PasswordEncoder는 BcryptPasswordEncoder 사용
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf(csrf -> csrf.disable())
//
//                .exceptionHandling()
//                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                    .accessDeniedHandler(jwtAccessDeniedHandler)
//                //h2 사용
//                .and()
//                .headers()
//                .frameOptions()
//                .sameOrigin()
//                //세션 사용하지 않으니 STATELESS
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers("/authenticate").permitAll()
//                .requestMatchers("/signup").permitAll()
//                .requestMatchers(PathRequest.toH2Console()).permitAll()
//                .anyRequest().authenticated()
//
//                .and()
//                .apply(new JwtSecurityConfig(tokenProvider));
//
//        return httpSecurity.build();
//    }
//}
