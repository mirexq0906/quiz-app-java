package com.example.modules.modules.repository;

import com.example.modules.modules.domain.ModuleItem;

import java.util.List;
import java.util.Optional;

public interface ModuleItemRepository {

    List<ModuleItem> findModuleItemsByModuleId(Long moduleId);

    Optional<ModuleItem> findModuleItemById(Long id);

    ModuleItem addItemToModule(ModuleItem moduleItem);

    ModuleItem updateItemToModule(ModuleItem moduleItem);

    void removeItemFromModule(Long moduleItemId);

    int count();

}
