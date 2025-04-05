package com.example.modules.users.service;

import com.example.modules.users.domain.User;
import com.example.modules.users.web.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findById(Long id);

    User update(UserDto userDto, Long id);

}
