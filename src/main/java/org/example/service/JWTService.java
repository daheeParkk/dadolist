package org.example.service;

public interface JWTService {

    String refreshTokenReissue(Long id);

    String accessTokenReissue(String refreshToken);
}
