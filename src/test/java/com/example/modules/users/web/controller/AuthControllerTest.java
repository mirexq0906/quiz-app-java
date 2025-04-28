package com.example.modules.users.web.controller;

import com.example.AbstractControllerTest;
import com.example.modules.users.domain.Role;
import com.example.modules.users.repository.UserRepository;
import com.example.modules.users.service.UserService;
import com.example.modules.users.web.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class AuthControllerTest extends AbstractControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void login_shouldLoginUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("test");
        userDto.setPassword("123456");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(userDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accessToken").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.refreshToken").isString());
    }

    @Test
    public void register_shouldUserRegister() throws Exception {
        Assertions.assertEquals(1, this.userRepository.count());

        UserDto userDto = new UserDto();
        userDto.setUsername("test1");
        userDto.setEmail("test1@example.com");
        userDto.setPassword("123456");
        userDto.setRole(Role.USER);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(userDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(userDto.getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(userDto.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role").value(userDto.getRole().toString()));

        Assertions.assertEquals(2, this.userRepository.count());
    }

}
