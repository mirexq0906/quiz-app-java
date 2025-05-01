package com.example.modules.modules.service.impl;

import com.example.modules.modules.domain.Module;
import com.example.modules.modules.service.FileProcessorService;
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

    private ModuleService moduleService;

    @Override
    public void readFile(MultipartFile file) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try (InputStream stream = file.getInputStream()) {
            byte[] bytes = new byte[1024];
            int bytesRead;
            StringBuilder content = new StringBuilder();

            while ((bytesRead = stream.read(bytes)) != -1) {
                System.out.println(bytesRead);
                content.append(new String(bytes, 0, bytesRead));
            }

            String[] modulesStr = content.toString().split("\\Q((|||))\\E");
            for (int i = 0; i < modulesStr.length; i++) {
                String[] moduleStr = modulesStr[i].split("\\Q((|))\\E");

                if (moduleStr.length == 2) {
                    com.example.modules.modules.domain.Module module = Module.builder()
                            .title(moduleStr[0])
                            .description(moduleStr[1])
                            .userId(user.getId())
                            .build();

                    System.out.println(module);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
