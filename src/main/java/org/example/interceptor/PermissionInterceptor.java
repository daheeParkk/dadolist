package org.example.interceptor;

import lombok.RequiredArgsConstructor;
import org.example.annotation.NoAuth;
import org.example.annotation.Permission;
import org.example.domain.Token;
import org.example.domain.User;
import org.example.dto.token.JwtToken;
import org.example.exception.UnauthorizedException;
import org.example.exception.UnprivilegedAPIException;
import org.example.repository.TokenMapper;
import org.example.util.JWTUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
public class PermissionInterceptor implements HandlerInterceptor {

    private final JWTUtil jwtUtil;

    private final TokenMapper tokenMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Permission permission = handlerMethod.getMethodAnnotation(Permission.class);

        NoAuth noAuth = handlerMethod.getMethodAnnotation(NoAuth.class);

        if (noAuth != null) {
            return true;
        }

        if (request.getHeader("Authorization") == null)
            throw new UnauthorizedException("Token does not exist");

        String accessToken = (request.getHeader("Authorization")).substring(7);
        User user = jwtUtil.validate(accessToken);

        if (tokenMapper.getRefreshToken(user.getId()) == null) {
            throw new UnauthorizedException("Try 'login' again");
        }

        if (user.getAuthority() == null) {
            throw new UnauthorizedException("Unable to find user by token");
        }

        if (permission.role().equals(Permission.PermissionRole.ADMIN)) {    // 관리자 API
            if (user.getAuthority() == null) {        // 권한이 없을 경우
                throw new UnprivilegedAPIException("not authorized");
            } else if (user.getAuthority() == 0) {    // 권한이 0 (유저)일 경우
                throw new UnprivilegedAPIException("not authorized");
            }
        }

        if (permission.role().equals(Permission.PermissionRole.USER)) {      // 유저 API
            if (user.getAuthority() == null) {
                throw new UnprivilegedAPIException("not authorized");
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
