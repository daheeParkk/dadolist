package org.example.util;

import io.jsonwebtoken.*;
import org.example.domain.User;
import org.example.dto.token.JwtToken;
import org.example.dto.user.UserAndToken;
import org.example.repository.TokenMapper;
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

    private final TokenMapper tokenMapper;

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

            int refreshExpiredTime = 1000 * 60 * 60 * 24 * 30;    // 30일
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

    // 토큰에 담긴 정보 가져오기
    public Claims getClaims(String token) throws Exception{

            Claims claims = Jwts.parser()
                    .setSigningKey(key.getBytes())
                    .parseClaimsJws(token).getBody();
        return claims;
    }

    // 토큰 유효성 검증
    public boolean isTokenExpired(String token){
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(key.getBytes()).parseClaimsJws(token).getBody();
            return true;
        } catch (ExpiredJwtException e) {  // Token이 만료된 경우
            return false;
        } catch (JwtException e){  // Token이 변조된 경우
            return false;
        }
    }

    public UserAndToken validate(String accessToken){

        Claims body = Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(accessToken).getBody();
        System.out.println(body);
        UserAndToken userAndToken = new UserAndToken();
        Long userId;
        try {
            userId = Long.parseLong(body.getSubject());
            System.out.println(userId);
            User user = userMapper.selectUser(userId);
            System.out.println(user);
            userAndToken.setUser(user);
            System.out.println(userAndToken.getUser());
            System.out.println(userAndToken.getAccessToken());

        } catch (NumberFormatException e){
            userAndToken.setAccessToken(null);
            System.out.println("NumberFormatException");

        } catch (Exception e){
            userAndToken.setAccessToken(null);
            System.out.println("Exception");

        }

        if (!isTokenExpired(accessToken)) {                                // accessToken이 만료되었을 때
            String refreshToken = tokenMapper.getRefreshToken(userAndToken.getUser().getId());
            if (!isTokenExpired(refreshToken)){                            // refreshToken도 만료 -> DB에서 삭제
                tokenMapper.deleteRefreshToken(true, userAndToken.getUser().getId());
                userAndToken.setAccessToken(null);
            } else {                                                       // refreshToken은 만료 X -> accessToken 재발급
                JwtToken jwtToken = crateToken(userAndToken.getUser().getId(), true, false);
                String reissueAccessToke = jwtToken.getAccessToken();
                userAndToken.setAccessToken(reissueAccessToke);
            }
            return userAndToken;

        }
        userAndToken.setAccessToken(accessToken);
        System.out.println(userAndToken.getAccessToken());
        return userAndToken;
    }

}
