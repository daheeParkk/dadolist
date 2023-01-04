package org.example.interceptor;

import lombok.RequiredArgsConstructor;
import org.example.annotation.NoAuth;
import org.example.annotation.Permission;
import org.example.dto.user.UserAndToken;
import org.example.util.JWTUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class PermissionInterceptor implements HandlerInterceptor {

    private final JWTUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Permission permission = handlerMethod.getMethodAnnotation(Permission.class);
        NoAuth noAuth = handlerMethod.getMethodAnnotation(NoAuth.class);
        if(noAuth != null) {
            return true;
        }

        if (request.getHeader("Authorization") == null)
            return false;

        String accessToken;
        UserAndToken userAndToken;

            accessToken = request.getHeader("Authorization");
            userAndToken = jwtUtil.validate(accessToken);

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
