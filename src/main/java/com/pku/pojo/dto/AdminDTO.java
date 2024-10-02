package com.pku.pojo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminDTO {
    private String name;
    private String password;
}
