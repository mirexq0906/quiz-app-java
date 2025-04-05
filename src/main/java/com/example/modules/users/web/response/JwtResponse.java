package com.example.modules.users.web.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {

    private String accessToken;

    private String refreshToken;

}
