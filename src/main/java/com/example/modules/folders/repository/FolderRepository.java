package com.example.modules.folders.repository;

import com.example.modules.folders.domain.Folder;

import java.util.List;
import java.util.Optional;

public interface FolderRepository {

    Optional<Folder> findById(Long id);

    List<Folder> findAll();

    Folder create(Folder folder);

    Folder update(Folder folder);

    void delete(Long id);

    Integer count();

}
