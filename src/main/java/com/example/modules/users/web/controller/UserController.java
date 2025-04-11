package com.example.modules.users.web.controller;

import com.example.modules.users.service.UserService;
import com.example.modules.users.web.dto.UserDto;
import com.example.modules.users.web.mapper.UserMapper;
import com.example.modules.users.web.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.userMapper.userToResponse(
                        this.userService.findById(id)
                )
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.userMapper.userToResponse(
                        this.userService.update(userDto, id)
                )
        );
    }

}
