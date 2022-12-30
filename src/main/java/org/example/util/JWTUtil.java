package org.example.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {
    @Value("${jwt.secret_key}")
    private String key;

    public String crateToken(Long id) {

        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("data", id);

        Long expiredTime = 1000 * 60L * 60L * 2L;

        Date ext = new Date();
        ext.setTime(ext.getTime() + expiredTime);

        String jwt = Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setExpiration(ext)
                .signWith(SignatureAlgorithm.HS256, key.getBytes())
                .compact();

        return jwt;

    }

}
