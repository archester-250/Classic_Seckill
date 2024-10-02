package com.pku.pojo.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminLoginVO {
    private Long adminId;
    private String token;
}
