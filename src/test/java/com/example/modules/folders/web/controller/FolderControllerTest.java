package com.example.modules.folders.web.controller;

import com.example.AbstractControllerTest;
import com.example.modules.folders.repository.FolderRepository;
import com.example.modules.folders.web.dto.FolderDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class FolderControllerTest extends AbstractControllerTest {

    @Autowired
    private FolderRepository folderRepository;

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void findAll_shouldReturnFolders() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/folder"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.folders.length()").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.folders[0].length()").value(5));
    }

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void findById_shouldReturnFolder() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/folder/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("folder 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("description 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.updatedAt").isString());
    }

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void create_shouldReturnCreatedFolder() throws Exception {
        Integer countFolders = this.folderRepository.count();
        FolderDto folderDto = new FolderDto();
        folderDto.setTitle("title folder");
        folderDto.setDescription("desc folder");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/folder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(folderDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(11))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(folderDto.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(folderDto.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.updatedAt").isString());

        Assertions.assertEquals(countFolders + 1, this.folderRepository.count());
    }

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void update_shouldReturnUpdatedFolder() throws Exception {
        FolderDto folderDto = new FolderDto();
        folderDto.setTitle("title folder updated");
        folderDto.setDescription("desc folder updated");

        this.mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/folder/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(folderDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(folderDto.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(folderDto.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.updatedAt").isString());
    }

    @Test
    @WithUserDetails(value = "test", userDetailsServiceBeanName = "userServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void delete_shouldReturnDeletedFolder() throws Exception {
        Integer countFolders = this.folderRepository.count();
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/folder/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        Assertions.assertEquals(countFolders - 1, this.folderRepository.count());
    }

}
