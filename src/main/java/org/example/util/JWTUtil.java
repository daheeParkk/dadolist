package org.example.util;

import io.jsonwebtoken.*;
import org.example.domain.User;
import org.example.dto.token.JwtToken;
import org.example.dto.token.TokenUserId;
import org.example.dto.user.UserAndToken;
import org.example.exception.UnauthorizedException;
import org.example.repository.TokenMapper;
import org.example.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

    private final UserMapper userMapper;

    private final TokenMapper tokenMapper;

    public TokenUserId tokenUserId;

    @Autowired
    public JWTUtil(UserMapper userMapper, TokenMapper tokenMapper) {
        this.userMapper = userMapper;
        this.tokenMapper = tokenMapper;
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
    public TokenUserId isTokenExpired(String token, HttpServletRequest request) {

        try{
            System.out.println("in isTokenExpired");
            Claims claims = Jwts.parser()
                    .setSigningKey(key.getBytes()).parseClaimsJws(token).getBody();

            tokenUserId.setUserId(Long.parseLong(claims.getSubject()));
            tokenUserId.setIsAccessToken(true);
            return tokenUserId;
        } catch (ExpiredJwtException e) {  // Token이 만료된 경우

            try {

                String refreshToken = (request.getHeader("RefreshToken")).substring(7);

                Claims claims = Jwts.parser()
                        .setSigningKey(key.getBytes()).parseClaimsJws(refreshToken).getBody();

                tokenUserId.setUserId(Long.parseLong(claims.getSubject()));
                tokenUserId.setIsAccessToken(false);
                return tokenUserId;

            } catch (ExpiredJwtException expiredJwtException) {

                throw new UnauthorizedException("expired refresh token");
            }

        } catch (JwtException e) {  // Token이 변조된 경우
            throw new UnauthorizedException("Unauthorized");
        }
    }

    public UserAndToken validate(String accessToken, HttpServletRequest request){

        System.out.println("in");
        UserAndToken userAndToken = new UserAndToken();

        TokenUserId tokenUserId = isTokenExpired(accessToken, request);

        if (!tokenUserId.getIsAccessToken()) { // accessToken 만료 -> 재발급

            JwtToken jwtToken = crateToken(tokenUserId.getUserId(), true, false);
            String reissueAccessToke = jwtToken.getAccessToken();
            userAndToken.setAccessToken(reissueAccessToke);
        }

        userAndToken.setAccessToken(accessToken);
        return userAndToken;

    }

}
