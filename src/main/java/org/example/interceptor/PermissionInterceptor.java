package org.example.interceptor;

import io.jsonwebtoken.Claims;
import org.example.annotation.Permission;
import org.example.domain.User;
import org.example.dto.user.UserAndToken;
import org.example.service.UserService;
import org.example.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermissionInterceptor implements HandlerInterceptor {

    private final JWTUtil jwtUtil;

    @Autowired
    public PermissionInterceptor(JWTUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Permission permission = handlerMethod.getMethodAnnotation(Permission.class);
        if(permission == null) {
            return true;
        }

        if (request.getHeader("Authorization") == null || !(request.getHeader("Authorization")).startsWith("Bearer "))
            return false;

        String accessToken = (request.getHeader("Authorization")).substring(7);
        UserAndToken userAndToken = jwtUtil.validate(accessToken);

        if (userAndToken.getAccessToke() == null) {
            return false;
        }

        response.setHeader("Authorization", userAndToken.getAccessToke());

        if (permission.role().equals(Permission.PermissionRole.ADMIN)) {    // 관리자 API
            if (userAndToken.getUser().getAuthority() == null) {        // 권한이 없을 경우
                return false;
            } else if (userAndToken.getUser().getAuthority() == 0) {    // 권한이 0 (유저)일 경우
                return false;
            }
        }

        if(permission.role().equals(Permission.PermissionRole.USER)) {      // 유저 API
            if (userAndToken.getUser().getAuthority() == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
