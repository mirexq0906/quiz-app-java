package com.example.modules.modules.web.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ModuleItemListResponse {

    private List<ModuleItemResponse> moduleItems;

}
