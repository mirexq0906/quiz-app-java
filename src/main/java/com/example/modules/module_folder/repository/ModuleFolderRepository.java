package com.example.modules.module_folder.repository;

import com.example.modules.modules.domain.Module;

import java.util.List;

public interface ModuleFolderRepository {

    List<Module> findModulesByFolderId(Long folderId);

    void create(Long moduleId, Long folderId);

    void delete(Long moduleId, Long folderId);

}
