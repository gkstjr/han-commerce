package com.toy.hancommerce.controller;

import com.toy.hancommerce.model.User;
import com.toy.hancommerce.model.dto.UserDto;
import com.toy.hancommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//회원 가입 및 사용자 정보 조회
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userservice;

    @PostMapping("/users/register")
    public ResponseEntity<User> signup(
            @RequestBody @Valid UserDto userDto
            )    {
        return new ResponseEntity<>(userservice.signup(userDto), HttpStatus.CREATED);
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userservice.findAll(),HttpStatus.OK);
    }

    @GetMapping("/users/my-info")
    public ResponseEntity<User> getMyUserInfo() {
        return ResponseEntity.ok(userservice.getMyUserWithAuthorities().get());
    }
    @PutMapping("/users/my-info")
    public ResponseEntity<User> updateMyUserInfo(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userservice.updateMyUserInfo(userDto),HttpStatus.OK);
    }
    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userservice.getUserWithAuthorities(username).get());
    }


}
