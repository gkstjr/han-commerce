package com.toy.hancommerce.controller;

import com.toy.hancommerce.model.User;
import com.toy.hancommerce.model.dto.UserDto;
import com.toy.hancommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//회원 가입 및 사용자 정보 조회
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userservice;

    @PostMapping("/users/register")
    public ResponseEntity<User> signup(
            @RequestBody @Valid UserDto userDto
//            BindingResult bindingResult
            )    {
//        if(bindingResult.hasErrors()) {
//            return ResponseEntity.badRequest().build();
//        }
        return new ResponseEntity<>(userservice.signup(userDto), HttpStatus.OK);
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
