package com.example.modules.modules.web.controller;

import com.example.AbstractControllerTest;
import com.example.modules.modules.repository.ModuleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class ModuleControllerTest extends AbstractControllerTest {

    @Autowired
    private ModuleRepository moduleRepository;

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void findAll_shouldReturnModules() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/module"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.modules.length()").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.modules[0].length()").value(6));
    }

}
