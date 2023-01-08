package org.example.service;

import org.example.dto.token.JwtToken;
import org.example.repository.TokenMapper;
import org.example.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JWTServiceImpl implements JWTService {

    private final JWTUtil jwtUtil;

    private final TokenMapper tokenMapper;

    @Autowired
    public JWTServiceImpl(JWTUtil jwtUtil, TokenMapper tokenMapper) {

        this.jwtUtil = jwtUtil;
        this.tokenMapper = tokenMapper;
    }

    @Override
    public String refreshTokenReissue(Long id) {

        JwtToken jwtToken = jwtUtil.crateToken(id, false, true);
        tokenMapper.createRefreshToken(id, jwtToken.getRefreshToken());

        return jwtToken.getRefreshToken();
    }

    @Override
    public String accessTokenReissue(String refreshToken) {

        Long userId = jwtUtil.isTokenExpired(refreshToken);
        JwtToken jwtToken = jwtUtil.crateToken(userId, true, false);

        return jwtToken.getAccessToken();
    }
}
