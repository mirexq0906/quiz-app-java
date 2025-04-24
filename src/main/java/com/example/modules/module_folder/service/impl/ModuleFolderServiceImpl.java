package com.example.modules.module_folder.service.impl;

import com.example.modules.module_folder.repository.ModuleFolderRepository;
import com.example.modules.module_folder.service.ModuleFolderService;
import com.example.modules.modules.domain.Module;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleFolderServiceImpl implements ModuleFolderService {

    private final ModuleFolderRepository moduleFolderRepository;

    @Override
    public List<Module> findModulesByFolderId(Long folderId) {
        return this.moduleFolderRepository.findModulesByFolderId(folderId);
    }

    @Override
    public void addModuleToFolder(Long moduleId, Long folderId) {
        this.moduleFolderRepository.create(moduleId, folderId);
    }

    @Override
    public void removeModuleFromFolder(Long moduleId, Long folderId) {
        this.moduleFolderRepository.delete(moduleId, folderId);
    }

}
