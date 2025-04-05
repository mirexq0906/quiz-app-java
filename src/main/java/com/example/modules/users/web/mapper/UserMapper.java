package com.example.modules.users.web.mapper;

import com.example.modules.users.domain.User;
import com.example.modules.users.web.dto.UserDto;
import com.example.modules.users.web.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User requestToUser(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .build();
    }

    public User requestToUser(UserDto userDto, Long id) {
        User user = this.requestToUser(userDto);
        user.setId(id);
        return user;
    }

    public UserResponse userToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .createdAt(user.getCreateTime().toString())
                .updatedAt(user.getUpdateTime().toString())
                .build();
    }

}
