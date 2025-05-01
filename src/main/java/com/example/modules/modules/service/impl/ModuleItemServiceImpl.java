package com.example.modules.modules.service.impl;

import com.example.exception.DomainNotFoundException;
import com.example.modules.modules.domain.ModuleItem;
import com.example.modules.modules.repository.ModuleItemRepository;
import com.example.modules.modules.service.ModuleItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModuleItemServiceImpl implements ModuleItemService {

    private final ModuleItemRepository moduleItemRepository;

    @Override
    public List<ModuleItem> findModuleItemsByModuleId(Long moduleId) {
        return this.moduleItemRepository.findModuleItemsByModuleId(moduleId);
    }

    @Override
    public ModuleItem findModuleItemById(Long id) {
        return this.moduleItemRepository.findModuleItemById(id)
                .orElseThrow(() -> new DomainNotFoundException("module item not found"));
    }

    @Override
    public ModuleItem addItemToModule(ModuleItem moduleItem) {
        return this.moduleItemRepository.addItemToModule(moduleItem);
    }

    @Override
    public ModuleItem updateItemToModule(ModuleItem moduleItem) {
        ModuleItem updatedModuleItem = this.findModuleItemById(moduleItem.getId());
        Optional.ofNullable(moduleItem.getTitle()).ifPresent(updatedModuleItem::setTitle);
        Optional.ofNullable(moduleItem.getDescription()).ifPresent(updatedModuleItem::setDescription);
        Optional.ofNullable(moduleItem.getCode()).ifPresent(updatedModuleItem::setCode);
        return this.moduleItemRepository.updateItemToModule(updatedModuleItem);
    }

    @Override
    public void removeItemFromModule(Long moduleItemId) {
        this.moduleItemRepository.removeItemFromModule(moduleItemId);
    }

}
