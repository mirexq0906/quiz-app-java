package com.example.modules.modules.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ModuleItem {

    private Long id;
    private String title;
    private String description;
    private String code;
    private Long moduleId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
