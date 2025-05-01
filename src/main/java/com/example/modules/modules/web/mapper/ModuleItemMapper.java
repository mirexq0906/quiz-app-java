package com.example.modules.modules.web.mapper;

import com.example.modules.modules.domain.ModuleItem;
import com.example.modules.modules.web.dto.ModuleItemDto;
import com.example.modules.modules.web.response.ModuleItemListResponse;
import com.example.modules.modules.web.response.ModuleItemResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModuleItemMapper {

    public ModuleItem requestToModuleItem(ModuleItemDto moduleItemDto) {
        return ModuleItem.builder()
                .title(moduleItemDto.getTitle())
                .description(moduleItemDto.getDescription())
                .code(moduleItemDto.getCode())
                .moduleId(moduleItemDto.getModuleId())
                .build();
    }

    public ModuleItem requestToModuleItem(ModuleItemDto moduleItemDto, Long id) {
        ModuleItem moduleItem = this.requestToModuleItem(moduleItemDto);
        moduleItem.setId(id);
        return moduleItem;
    }

    public ModuleItemResponse moduleItemToResponse(ModuleItem moduleItem) {
        return ModuleItemResponse.builder()
                .id(moduleItem.getId())
                .title(moduleItem.getTitle())
                .description(moduleItem.getDescription())
                .code(moduleItem.getCode())
                .createdAt(moduleItem.getCreatedAt())
                .updatedAt(moduleItem.getUpdatedAt())
                .build();
    }

    public ModuleItemListResponse moduleItemListToListResponse(List<ModuleItem> moduleItems) {
        return ModuleItemListResponse.builder()
                .moduleItems(moduleItems.stream().map(this::moduleItemToResponse).toList())
                .build();
    }

}
