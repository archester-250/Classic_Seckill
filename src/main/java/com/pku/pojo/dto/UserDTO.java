package com.pku.pojo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {
    private String username;
    private String password;
}
