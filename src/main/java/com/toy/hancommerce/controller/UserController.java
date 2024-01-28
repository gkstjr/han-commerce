package com.toy.hancommerce.controller;

import com.toy.hancommerce.model.User;
import com.toy.hancommerce.model.dto.UserDto;
import com.toy.hancommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//회원 가입 및 사용자 정보 조회
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userservice;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(
            @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(userservice.signup(userDto));
    }

    @GetMapping("/users")
    public ResponseEntity<User> getMyUserInfo() {
        return ResponseEntity.ok(userservice.getMyUserWithAuthorities().get());
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userservice.getUserWithAuthorities(username).get());
    }

}
