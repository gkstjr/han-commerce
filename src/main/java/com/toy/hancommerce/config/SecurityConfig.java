package com.toy.hancommerce.config;

import com.toy.hancommerce.jwt.JwtAccessDeniedHandler;
import com.toy.hancommerce.jwt.JwtAuthenticationEntryPoint;
import com.toy.hancommerce.jwt.JwtSecurityConfig;
import com.toy.hancommerce.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    //PasswordEncoder는 BcryptPasswordEncoder 사용
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

     httpSecurity
                .csrf((csrfConfig) ->
                        csrfConfig.disable()
                )
                .exceptionHandling((exceptionConfig) ->
                        exceptionConfig.authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(jwtAccessDeniedHandler)
                )

                //h2 사용
                .headers((headerConfig) ->
                        headerConfig.frameOptions(frameOptionsConfig ->
                                frameOptionsConfig.sameOrigin()
                        )
                )

                //세션 사용하지 않으니 STATELESS
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/users/login", "/users/register","/error","/swagger-ui/**","/swagger-resources/**","/v3/api-docs/**").permitAll()
                                .requestMatchers("/users/my-info").hasAnyRole("ADMIN","USER")
                                .requestMatchers("/users","/users/{username}","/categorys").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/categorys").hasAnyRole("ADMIN","USER")
                                .requestMatchers(PathRequest.toH2Console()).permitAll()
                                .anyRequest().authenticated()
                )

             .with(new JwtSecurityConfig(tokenProvider), customizer -> {});

        return httpSecurity.build();
    }
}
