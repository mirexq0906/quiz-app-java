package com.example.modules.folders.web.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FolderListResponse {

    private List<FolderResponse> folders;

}
