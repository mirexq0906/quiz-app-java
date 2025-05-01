package com.example.modules.modules.web.controller;

import com.example.AbstractControllerTest;
import com.example.modules.modules.repository.ModuleItemRepository;
import com.example.modules.modules.web.dto.ModuleItemDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class ModuleItemControllerTest extends AbstractControllerTest {

    @Autowired
    private ModuleItemRepository moduleItemRepository;

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void findModuleItemsByModuleId_shouldReturnModuleItems() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/module-item/by-module/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.moduleItems.length()").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.moduleItems[0].length()").value(6));
    }

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void findModuleItemById_shouldReturnModuleItem() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/module-item/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("module_item 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("description 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("code 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.updatedAt").isString());
    }

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void addItemToModule_shouldReturnModuleItem() throws Exception {
        int countModuleItems = this.moduleItemRepository.count();
        ModuleItemDto moduleItemDto = new ModuleItemDto();
        moduleItemDto.setTitle("module_item title");
        moduleItemDto.setDescription("module_item description");
        moduleItemDto.setCode("module_item code");
        moduleItemDto.setModuleId(1L);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/module-item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(moduleItemDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(moduleItemDto.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(moduleItemDto.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(moduleItemDto.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.updatedAt").isString());

        Assertions.assertEquals(countModuleItems + 1, this.moduleItemRepository.count());
    }

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void updateItemToModule_shouldReturnUpdatedModuleItem() throws Exception {
        ModuleItemDto moduleItemDto = new ModuleItemDto();
        moduleItemDto.setTitle("title updated");
        moduleItemDto.setDescription("desc updated");
        moduleItemDto.setCode("code updated");
        this.mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/module-item/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(moduleItemDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(moduleItemDto.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(moduleItemDto.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(moduleItemDto.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.updatedAt").isString());
    }

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void removeItemFromModule_shouldReturnVoid() throws Exception {
        int countModuleItems = this.moduleItemRepository.count();

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/module-item/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Assertions.assertEquals(countModuleItems - 1, this.moduleItemRepository.count());
    }

}
