package com.example.modules.modules.service;

import com.example.modules.modules.domain.ModuleItem;

import java.util.List;

public interface ModuleItemService {

    List<ModuleItem> findModuleItemsByModuleId(Long moduleId);

    ModuleItem findModuleItemById(Long id);

    ModuleItem addItemToModule(ModuleItem moduleItem);

    ModuleItem updateItemToModule(ModuleItem moduleItem);

    void removeItemFromModule(Long moduleItemId);

}
