package com.example.modules.users.web.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private Long id;

    private String username;

    private String email;

    private String role;

    private String createdAt;

    private String updatedAt;

}
