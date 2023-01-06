package org.example.controller;

import org.example.annotation.Permission;
import org.example.service.JWTService;
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
    public JWTController(JWTService jwtService){
        this.jwtService = jwtService;
    }


    @Permission(role = Permission.PermissionRole.USER)
    @GetMapping("/{id}")
    public ResponseEntity<String> refreshTokenReissue(@PathVariable("id") Long id){

        return new ResponseEntity<>(jwtService.refreshTokenReissue(id), HttpStatus.OK);
    }

    @Permission(role = Permission.PermissionRole.USER)
    @GetMapping
    public ResponseEntity<String> accessTokenReissue(HttpServletRequest request){

        String refreshToken = (request.getHeader("Authorization")).substring(7);

        return new ResponseEntity<>(jwtService.accessTokenReissue(refreshToken), HttpStatus.OK);

    }

}
