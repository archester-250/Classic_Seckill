package com.pku.pojo.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserLoginVO {
    private Long userId;
    private String token;
}
