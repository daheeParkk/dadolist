package org.example.controller;

import org.example.annotation.Permission;
import org.example.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public JWTController(JWTService jwtService){
        this.jwtService = jwtService;
    }


    @Permission(role = Permission.PermissionRole.USER)
    @GetMapping("/refresh/{id}")
    public void refreshTokenReissue(@PathVariable("id") Long id){

        String refreshToken = jwtService.refreshTokenReissue(id);
    }

    @Permission(role = Permission.PermissionRole.USER)
    @GetMapping("/access")
    public void accessTokenReissue(HttpServletRequest request){

        String refreshToken = (request.getHeader("Authorization")).substring(7);
        String accessToken = jwtService.accessTokenReissue(refreshToken);

    }

}
