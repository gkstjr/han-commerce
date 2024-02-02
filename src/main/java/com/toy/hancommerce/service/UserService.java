package com.toy.hancommerce.service;

import com.toy.hancommerce.error.ErrorCode;
import com.toy.hancommerce.error.MyException;
import com.toy.hancommerce.jwt.SecurityUtil;
import com.toy.hancommerce.model.Address;
import com.toy.hancommerce.model.Authority;
import com.toy.hancommerce.model.User;
import com.toy.hancommerce.model.dto.UserDto;
import com.toy.hancommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User signup(UserDto userDto) {
        if(userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new MyException(ErrorCode.DUPLICATED_USER_NAME);
        }

        //가입되어 있지 않은 회원이면,
        //권한 정보 만들고
        Authority authority = Optional.ofNullable(userDto.getAuth())
                .filter(auth -> auth.equals("admin"))
                .map(auth -> Authority.builder().authorityName("ROLE_ADMIN").build())
                .orElse(Authority.builder().authorityName("ROLE_USER").build());

        //유저 정보를 만들어서 save
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .authorities(Collections.singleton(authority))
                .address(new Address(userDto.getCity() , userDto.getStreet(), userDto.getZipcode()))
                .build();

        return userRepository.save(user);
    }

    //유저 , 권한 정보를 가져오는 메소드
    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String username) {
        return Optional.of(userRepository.findOneWithAuthoritiesByUsername(username)
                .orElseThrow(() -> new MyException(ErrorCode.USERNAME_NOT_FOUND)));
    }

    // 현재 securityContext에 저장된 username의 정보만 가져오는 메소드
    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }

    //전체 유저 가져오는 메소드
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Transactional
    public User updateMyUserInfo(UserDto userDto) {
        //현재 사용자의 username 뽑기
        User user = Optional.of(SecurityUtil.getCurrentUsername()
                        .flatMap(userRepository::findOneWithAuthoritiesByUsername))
                .orElseThrow(() -> new MyException(ErrorCode.USERNAME_NOT_FOUND)).get();

        // 수정 요청 값들만 변경 로직\
        if (userDto.getAuth() != null) {
            user.getAuthorities().clear();
            if (userDto.getAuth().equals("admin")) {
                user.getAuthorities().add(new Authority("ROLE_ADMIN"));
            } else {
                user.getAuthorities().add(new Authority("ROLE_USER"));
            }
        }
        String city = "";
        String street = "";
        String zipcode = "";
        if (userDto.getCity() != null) {
            city = userDto.getCity();
        }
        if (userDto.getStreet() != null) {
            street = userDto.getStreet();
        }
        if (userDto.getZipcode() != null) {
            zipcode = userDto.getZipcode();
        }

        if (!city.isEmpty() || !street.isEmpty() || !zipcode.isEmpty()) {
            user.setAddress(new Address(city,street,zipcode));
        }

        return userRepository.save(user);
    }
}
