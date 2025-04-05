package com.example.modules.users.web.dto;

import com.example.modules.users.domain.Role;
import lombok.Data;

@Data
public class UserDto {

    private String username;

    private String email;

    private String password;

    private Role role;

}
