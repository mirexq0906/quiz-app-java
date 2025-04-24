package com.example.modules.module_folder.service;

import com.example.modules.modules.domain.Module;

import java.util.List;

public interface ModuleFolderService {

    List<Module> findModulesByFolderId(Long folderId);

    void addModuleToFolder(Long moduleId, Long folderId);

    void removeModuleFromFolder(Long moduleId, Long folderId);

}
