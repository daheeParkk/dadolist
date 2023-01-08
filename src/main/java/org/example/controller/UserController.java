package org.example.controller;

import org.example.annotation.NoAuth;
import org.example.annotation.Permission;
import org.example.domain.Team;
import org.example.domain.User;
import org.example.dto.token.JwtToken;
import org.example.dto.user.UserVerification;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){

        this.userService = userService;

    }

    @PostMapping("/admin")
    public ResponseEntity<User> createAdmin(@RequestBody User user) {

        return new ResponseEntity<>(userService.createAdmin(user), HttpStatus.CREATED);
    }

    @NoAuth
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){

        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @NoAuth
    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody UserVerification userVerification){

        JwtToken jwtToken = userService.login(userVerification);
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);

    }

    @Permission(role = Permission.PermissionRole.USER)
    @GetMapping("/logout/{id}")
    public ResponseEntity<String> logout(@PathVariable("id") Long id) {

        userService.logout(id);
        return new ResponseEntity<>("Refresh token deleted", HttpStatus.NO_CONTENT);
    }

    @Permission(role = Permission.PermissionRole.USER)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){

        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted", HttpStatus.NO_CONTENT);

    }

    @NoAuth
    @GetMapping
    public ResponseEntity<List<User>> getUser(){

        return new ResponseEntity<>(userService.getUser(), HttpStatus.OK);
    }

    @Permission(role = Permission.PermissionRole.USER)
    @GetMapping("/{id}")
    public ResponseEntity<User> getInformation(@PathVariable("id") Long id){

        return new ResponseEntity<>(userService.getInformation(id), HttpStatus.OK);

    }

    @Permission(role = Permission.PermissionRole.USER)
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){

        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }

    @Permission(role = Permission.PermissionRole.USER)
    @PostMapping("/{id}/team")
    public ResponseEntity<User> joinTeam(@PathVariable("id") Long id, @RequestBody Team team){

        return new ResponseEntity<>(userService.joinTeam(id, team.getTeamName()), HttpStatus.OK);

    }

    @Permission(role = Permission.PermissionRole.USER)
    @DeleteMapping("/{id}/team")
    public ResponseEntity<User> leaveTeam(@PathVariable("id") Long id, @RequestBody Team team) {

        return new ResponseEntity<>(userService.leaveTeam(id, team.getTeamName()), HttpStatus.NO_CONTENT);
    }

}
