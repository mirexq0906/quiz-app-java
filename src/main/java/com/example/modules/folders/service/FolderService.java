package com.example.modules.folders.service;

import com.example.modules.folders.domain.Folder;

import java.util.List;

public interface FolderService {

    List<Folder> findAll();

    Folder findById(Long id);

    Folder create(Folder folder);

    Folder update(Folder folder);

    void delete(Long id);

}
