package com.pku.controller.user;

import com.pku.pojo.dto.UserDTO;
import com.pku.pojo.entity.User;
import com.pku.pojo.vo.UserLoginVO;
import com.pku.properties.JwtProperties;
import com.pku.result.Result;
import com.pku.service.UserService;
import com.pku.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/user")
@Slf4j
public class UserController {
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserDTO userDTO) {
        log.info("用户注册：{}", userDTO);
        userService.register(userDTO);
        return Result.success();
    }

    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserDTO userDTO) {
        log.info("用户登录：{}", userDTO);
        User user = userService.login(userDTO);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .userId(user.getUserId())
                .token(token)
                .build();
        return Result.success(userLoginVO);
    }
}
