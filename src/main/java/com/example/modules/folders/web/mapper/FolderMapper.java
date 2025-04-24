package com.example.modules.folders.web.mapper;

import com.example.modules.folders.domain.Folder;
import com.example.modules.folders.web.dto.FolderDto;
import com.example.modules.folders.web.response.FolderListResponse;
import com.example.modules.folders.web.response.FolderResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FolderMapper {

    public Folder requestToFolder(FolderDto folderDto) {
        return Folder.builder()
                .title(folderDto.getTitle())
                .description(folderDto.getDescription())
                .build();
    }

    public Folder requestToFolder(FolderDto folderDto, Long id) {
        Folder folder = this.requestToFolder(folderDto);
        folder.setId(id);
        return folder;
    }

    public FolderResponse folderToResponse(Folder folder) {
        return FolderResponse.builder()
                .id(folder.getId())
                .title(folder.getTitle())
                .description(folder.getDescription())
                .createdAt(folder.getCreatedAt())
                .updatedAt(folder.getUpdatedAt())
                .build();
    }

    public FolderListResponse folderListToResponse(List<Folder> folders) {
        return FolderListResponse.builder()
                .folders(folders.stream().map(this::folderToResponse).toList())
                .build();
    }

}
