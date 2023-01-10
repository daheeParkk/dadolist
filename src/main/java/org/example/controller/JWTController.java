package org.example.controller;

import io.swagger.annotations.ApiOperation;
import org.example.annotation.NoAuth;
import org.example.annotation.Permission;
import org.example.dto.token.JwtToken;
import org.example.service.JWTService;
import org.example.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/jwt")
public class JWTController {

    private final JWTService jwtService;

    @Autowired
    public JWTController(JWTService jwtService) {

        this.jwtService = jwtService;
    }

    @ApiOperation(value = "access token 재발급", notes = "access token과 refresh token 모두 재발급")
    @NoAuth
    @GetMapping
    public ResponseEntity<JwtToken> accessTokenReissue(HttpServletRequest request) {

        String refreshToken = (request.getHeader("Authorization")).substring(7);
        jwtService.deleteRefreshToken(refreshToken);

        return new ResponseEntity<>(jwtService.accessTokenReissue(refreshToken), HttpStatus.OK);
    }

}
