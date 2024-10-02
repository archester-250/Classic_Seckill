package com.pku.interceptor;

import com.pku.constant.JwtClaimsConstant;
import com.pku.context.BaseContext;
import com.pku.properties.JwtProperties;
import com.pku.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class JWTAdminInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("管理员拦截器生效");
        if(!(handler instanceof HandlerMethod)){ return true;}
        try {
            String token = request.getHeader("token");
            log.info("jwt token: {}", token);
            Jws<Claims> claimsJws = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            int adminId = (Integer)claimsJws.getPayload().get(JwtClaimsConstant.ADMIN_ID);
            log.info("adminId: {}", adminId);
            BaseContext.setCurrentId(adminId);
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
        BaseContext.removeCurrentId();
    }
}
