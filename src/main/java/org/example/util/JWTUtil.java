package org.example.util;

import io.jsonwebtoken.*;
import org.example.domain.User;
import org.example.dto.token.JwtToken;
import org.example.exception.UnauthorizedException;
import org.example.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

    private final UserMapper userMapper;

    @Autowired
    public JWTUtil(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Value("${jwt.secret_key}")
    private String key;

    public JwtToken crateToken(Long id, Boolean createAccess, Boolean createRefresh) {

        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("sub", id);

        JwtToken jwtToken = new JwtToken();

        if (createAccess){

            int accessExpiredTime = 1000 * 60 * 60;           // 1시간
            Date accessExt = new Date();
            accessExt.setTime(accessExt.getTime() + accessExpiredTime);

            String accessToken = Jwts.builder()
                    .setHeader(headers)
                    .setClaims(payloads)
                    .setExpiration(accessExt)
                    .signWith(SignatureAlgorithm.HS256, key.getBytes())
                    .compact();

            jwtToken.setAccessToken(accessToken);
        }

        if (createRefresh){

            int refreshExpiredTime = 1000 * 60 * 60 * 24 * 3;    // 3일
            Date refreshExt = new Date();
            refreshExt.setTime(refreshExt.getTime() + refreshExpiredTime);

            String refreshToken = Jwts.builder()
                    .setHeader(headers)
                    .setClaims(payloads)
                    .setExpiration(refreshExt)
                    .signWith(SignatureAlgorithm.HS256, key.getBytes())
                    .compact();

            jwtToken.setRefreshToken(refreshToken);
        }

        return jwtToken;

    }

    // 토큰 유효성 검증
    public Long isTokenExpired(String token) {

        try{

            Claims claims = Jwts.parser()
                    .setSigningKey(key.getBytes()).parseClaimsJws(token).getBody();

            Long userId = Long.parseLong(claims.getSubject());
            return userId;

        } catch (ExpiredJwtException e) {  // Token이 만료된 경우
            throw new UnauthorizedException("expired token");

        } catch (JwtException e) {  // Token이 변조된 경우
            throw new UnauthorizedException("Unauthorized");
        }
    }

    public User validate(String accessToken){

        Long userId = isTokenExpired(accessToken);
        User user = userMapper.selectUser(userId);

        return user;

    }

}
