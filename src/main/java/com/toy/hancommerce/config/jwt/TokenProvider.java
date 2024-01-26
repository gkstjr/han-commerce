package com.toy.hancommerce.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component // 라이브러리를 사용해서 JWT를 생성하고 검증하는 클래스
public class TokenProvider implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private static final String AUTHORITIES_KEY = "auth";
    private final String secret;
    private final long tokenValidityInMilliseconds;
    private Key key;

    public TokenProvider(@Value("${jwt.secret}") String secret,
                         @Value("${jwt.token-validity-in-secondes}") long tokenValidityInSeconds) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);

        logger.info("======================Security - TokenProvider-afterPropertiesSet=====================");

    }

    public String createToken(Authentication authentication) {
        logger.info("======================Security - TokenProvider-createToken=====================");

        //사용자의 권한 정보를 "," 구분된 하나의 문자열로 추출
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        //토큰의 expire 시간 설정
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities) //정보 저장
                .signWith(key , SignatureAlgorithm.HS512) // 암호화 알고리즘
                .setExpiration(validity)
                .compact();
    }


    //토큰으로 클레임을 만들고 이를 이용해 유저 객체를 만들어서 최종적으로 authentication 객체 리턴
    public Authentication getAuthentication(String token) {
        logger.info("======================Security - TokenProvider-getAuthentication=====================");

        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .toList();
        //시큐리티의 user 클래스 사용(아쉬운 점은 내 Entity 이름도 User라 좀 헷갈림 다음엔 Member로 해보자)
        org.springframework.security.core.userdetails.User principal
                = new User(claims.getSubject(),"" , authorities);

        return new UsernamePasswordAuthenticationToken(principal , token , authorities);
    }

    //토큰의 유효성 검증을 수행
    public boolean validateToken(String token) {
        logger.info("======================Security - TokenProvider-validateToken=====================");

        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {

            logger.info("잘못된 JWT 서명입니다.");
        }catch (ExpiredJwtException e) {

            logger.info("만료된 JWT 토큰입니다.");
        }catch (UnsupportedJwtException e) {

            logger.info("지원되지 않는 JWT 토근입니다.");
        }catch (IllegalArgumentException e) {

            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}
