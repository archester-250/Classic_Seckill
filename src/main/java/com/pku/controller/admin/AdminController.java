package com.pku.controller.admin;
import com.pku.pojo.dto.AdminDTO;
import com.pku.pojo.entity.Admin;
import com.pku.pojo.vo.AdminLoginVO;
import com.pku.properties.JwtProperties;
import com.pku.result.Result;
import com.pku.service.AdminService;
import com.pku.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("AdminProductController")
@Slf4j
@RequestMapping("/admin/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtProperties jwtProperties;
    @PostMapping("/login")
    public Result<AdminLoginVO> login(@RequestBody AdminDTO adminDTO) {
        log.info("管理员登录：{}", adminDTO);
        Admin admin = adminService.login(adminDTO);
        Map<String, Object> claims = new HashMap<>();
        claims.put("adminId", admin.getAdminId());
        String token = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);
        AdminLoginVO adminLoginVO = AdminLoginVO.builder()
                .adminId(admin.getAdminId())
                .token(token)
                .build();
        return Result.success(adminLoginVO);
    }
}
