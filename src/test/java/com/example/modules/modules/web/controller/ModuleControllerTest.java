package com.example.modules.modules.web.controller;

import com.example.AbstractControllerTest;
import com.example.modules.module_folder.service.ModuleFolderService;
import com.example.modules.modules.repository.ModuleRepository;
import com.example.modules.modules.web.dto.ModuleDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class ModuleControllerTest extends AbstractControllerTest {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ModuleFolderService moduleFolderService;

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void findAll_shouldReturnModules() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/module"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.modules.length()").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.modules[0].length()").value(5));
    }

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void findById_shouldReturnModule() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/module/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("module 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("description 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.updatedAt").isString());
    }

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void findByFolderId_shouldReturnModules() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/module/by-folder/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.modules.length()").value(3));
    }

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void create_shouldReturnCreatedModule() throws Exception {
        int countModules = this.moduleRepository.count();
        ModuleDto moduleDto = new ModuleDto();
        moduleDto.setTitle("module test");
        moduleDto.setDescription("description test");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/module")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(moduleDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(11))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(moduleDto.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(moduleDto.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.updatedAt").isString());

        Assertions.assertEquals(countModules + 1, this.moduleRepository.count());
    }

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void update_shouldReturnUpdatedModule() throws Exception {
        ModuleDto moduleDto = new ModuleDto();
        moduleDto.setTitle("module test updated");
        moduleDto.setDescription("description test updated");

        this.mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/module/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(moduleDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(moduleDto.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(moduleDto.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.updatedAt").isString());
    }

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void delete_shouldReturnVoid() throws Exception {
        int countModules = this.moduleRepository.count();
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/module/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Assertions.assertEquals(countModules - 1, this.moduleRepository.count());
    }

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void addToFolder_shouldReturnVoid() throws Exception {
        int countModulesByFolder = this.moduleFolderService.findModulesByFolderId(2L).size();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/module/1/folder/2"))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Assertions.assertEquals(countModulesByFolder + 1, this.moduleFolderService.findModulesByFolderId(2L).size());
    }

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void removeFromFolder_shouldReturnVoid() throws Exception {
        int countModulesByFolder = this.moduleFolderService.findModulesByFolderId(1L).size();

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/module/1/folder/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Assertions.assertEquals(countModulesByFolder - 1, this.moduleFolderService.findModulesByFolderId(1L).size());
    }

}
