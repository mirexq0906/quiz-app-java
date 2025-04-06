package com.example.modules.users.web.controller;

import com.example.modules.users.service.AuthService;
import com.example.modules.users.web.dto.UserDto;
import com.example.modules.users.web.mapper.UserMapper;
import com.example.modules.users.web.response.JwtResponse;
import com.example.modules.users.web.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authService;

    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.authService.login(userDto)
        );
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.userMapper.userToResponse(
                        this.authService.register(userDto)
                )
        );
    }

}
