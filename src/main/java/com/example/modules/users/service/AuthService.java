package com.example.modules.users.service;

import com.example.modules.users.domain.User;
import com.example.modules.users.web.dto.UserDto;
import com.example.modules.users.web.response.JwtResponse;

public interface AuthService {

    JwtResponse login(UserDto userDto);

    User register(UserDto userDto);

}
