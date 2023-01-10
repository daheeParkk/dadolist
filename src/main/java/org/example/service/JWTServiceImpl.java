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
    public JwtToken accessTokenReissue(String refreshToken) {

        Long userId = jwtUtil.isTokenExpired(refreshToken);
        JwtToken jwtToken = jwtUtil.crateToken(userId, true, true);
        tokenMapper.createRefreshToken(userId, jwtToken.getRefreshToken());

        return jwtToken;
    }

    @Override
    public void deleteRefreshToken(String refreshToken) {

        Long userId = jwtUtil.isTokenExpired(refreshToken);
        tokenMapper.deleteRefreshToken(userId);
    }
}
