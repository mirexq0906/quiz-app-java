package com.example.modules.modules.service.impl;

import com.example.exception.FileProcessorException;
import com.example.modules.modules.domain.Module;
import com.example.modules.modules.domain.ModuleItem;
import com.example.modules.modules.service.FileProcessorService;
import com.example.modules.modules.service.ModuleItemService;
import com.example.modules.modules.service.ModuleService;
import com.example.modules.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class FileProcessorServiceImpl implements FileProcessorService {

    private final ModuleService moduleService;
    private final ModuleItemService moduleItemService;

    @Override
    public void readFile(MultipartFile file, Long moduleId) {
        Module module = this.moduleService.findById(moduleId);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!module.getUserId().equals(user.getId())) {
            throw new FileProcessorException("user is not logged in");
        }

        try (InputStream stream = file.getInputStream()) {
            byte[] bytes = new byte[1024];
            int bytesRead;
            StringBuilder content = new StringBuilder();

            while ((bytesRead = stream.read(bytes)) != -1) {
                content.append(new String(bytes, 0, bytesRead));
            }

            String[] modulesStr = content.toString().split("\\Q((|||))\\E");
            for (int i = 0; i < modulesStr.length; i++) {
                String[] moduleStr = modulesStr[i].split("\\Q((|))\\E");

                if (moduleStr.length == 2) {
                    ModuleItem moduleItem = ModuleItem.builder()
                            .title(moduleStr[0])
                            .description(moduleStr[1])
                            .moduleId(moduleId)
                            .build();

                    this.moduleItemService.addItemToModule(moduleItem);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
