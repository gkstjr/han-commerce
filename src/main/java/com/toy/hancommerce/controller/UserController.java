package com.toy.hancommerce.controller;

import com.toy.hancommerce.model.User;
import com.toy.hancommerce.model.dto.UserDto;
import com.toy.hancommerce.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//회원 가입 및 사용자 정보 조회
@Tag(name = "USERS" , description = "계정관련 기능 API")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userservice;
    @Operation(summary = "회원가입")
    @PostMapping("/users/register")
    public ResponseEntity<User> signup(
            @RequestBody @Valid UserDto userDto
            )    {
        return new ResponseEntity<>(userservice.signup(userDto), HttpStatus.CREATED);
    }
    @Operation(summary = "전체 회원 정보 조회 - 관리자만")
    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userservice.findAll(),HttpStatus.OK);
    }

    @Operation(summary = "내 정보 조회 - 사용자 / 관리자")
    @GetMapping("/users/my-info")
    public ResponseEntity<User> getMyUserInfo() {
        return ResponseEntity.ok(userservice.getMyUserWithAuthorities().get());
    }
    @Operation(summary = "내 정보 수정 - 사용자 / 관리자")
    @PutMapping("/users/my-info")
    public ResponseEntity<User> updateMyUserInfo(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userservice.updateMyUserInfo(userDto),HttpStatus.OK);
    }
    @Operation(summary = "회원이름으로 회원 정보 조회 - 관리자만")
    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userservice.getUserWithAuthorities(username).get());
    }


}
