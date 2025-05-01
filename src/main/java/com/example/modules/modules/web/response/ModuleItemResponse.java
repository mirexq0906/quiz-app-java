package com.example.modules.modules.web.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ModuleItemResponse {

    private Long id;
    private String title;
    private String description;
    private String code;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
