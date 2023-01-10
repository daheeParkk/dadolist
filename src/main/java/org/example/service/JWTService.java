package org.example.service;

import org.example.dto.token.JwtToken;

public interface JWTService {

    JwtToken accessTokenReissue(String refreshToken);

    void deleteRefreshToken(String refreshToken);
}
