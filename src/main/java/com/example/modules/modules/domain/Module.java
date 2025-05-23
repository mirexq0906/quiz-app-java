package com.example.modules.modules.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Module {

    private Long id;
    private String title;
    private Long userId;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
