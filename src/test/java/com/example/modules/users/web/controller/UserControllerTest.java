package com.example.modules.users.web.controller;

import com.example.AbstractControllerTest;
import com.example.modules.users.web.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class UserControllerTest extends AbstractControllerTest {

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void findById_shouldReturnUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("test@mail.ru"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("USER"));
    }

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void update_shouldReturnUpdatedUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("testUpdated");
        userDto.setEmail("testUpdated@mail.ru");

        this.mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testUpdated"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("testUpdated@mail.ru"));
    }

}
