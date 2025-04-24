package com.example.modules.modules.web.mapper;

import com.example.modules.modules.domain.Module;
import com.example.modules.modules.web.dto.ModuleDto;
import com.example.modules.modules.web.response.ModuleListResponse;
import com.example.modules.modules.web.response.ModuleResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModuleMapper {

    public Module requestToModule(ModuleDto moduleDto) {
        return Module.builder()
                .title(moduleDto.getTitle())
                .description(moduleDto.getDescription())
                .code(moduleDto.getCode())
                .build();
    }

    public Module requestToModule(ModuleDto moduleDto, Long id) {
        Module module = this.requestToModule(moduleDto);
        module.setId(id);
        return module;
    }

    public ModuleResponse moduleToResponse(Module module) {
        return ModuleResponse.builder()
                .id(module.getId())
                .title(module.getTitle())
                .description(module.getDescription())
                .code(module.getCode())
                .createdAt(module.getCreatedAt())
                .updatedAt(module.getUpdatedAt())
                .build();
    }

    public ModuleListResponse moduleListToResponse(List<Module> modules) {
        return ModuleListResponse.builder()
                .modules(modules.stream().map(this::moduleToResponse).toList())
                .build();
    }

}
