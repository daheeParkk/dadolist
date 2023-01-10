package org.example.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @NoAuth
    @ApiOperation(value = "관리자 가입")
    @PostMapping("/admin")
    public ResponseEntity<User> createAdmin(@RequestBody User user) {

        return new ResponseEntity<>(userService.createAdmin(user), HttpStatus.CREATED);
    }

    @ApiOperation(value = "유저 가입")
    @NoAuth
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @ApiOperation(value = "로그인")
    @NoAuth
    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody UserVerification userVerification) {

        JwtToken jwtToken = userService.login(userVerification);
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }

    @ApiOperation(value = "로그아웃")
    @ApiImplicitParam(name = "id", value = "유저 식별 아이디")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Permission(role = Permission.PermissionRole.USER)
    @GetMapping(value = "/logout/{id}")
    public void logout(@PathVariable("id") Long id) {

        userService.logout(id);
    }

    @ApiOperation(value = "유저 삭제")
    @ApiImplicitParam(name = "id", value = "유저 식별 아이디")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Permission(role = Permission.PermissionRole.USER)
    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable("id") Long id) {

        userService.deleteUser(id);
    }

    @ApiOperation(value = "전체 유저 조회")
    @NoAuth
    @GetMapping
    public ResponseEntity<List<User>> getUser() {

        return new ResponseEntity<>(userService.getUser(), HttpStatus.OK);
    }

    @ApiOperation(value = "유저 조회")
    @ApiImplicitParam(name = "id", value = "유저 식별 아이디")
    @Permission(role = Permission.PermissionRole.USER)
    @GetMapping("/{id}")
    public ResponseEntity<User> getInformation(@PathVariable("id") Long id) {

        return new ResponseEntity<>(userService.getInformation(id), HttpStatus.OK);
    }

    @ApiOperation(value = "유저 업데이트")
    @ApiImplicitParam(name = "id", value = "유저 식별 아이디")
    @Permission(role = Permission.PermissionRole.USER)
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {

        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }

    @ApiOperation(value = "팀 참여")
    @ApiImplicitParam(name = "id", value = "유저 식별 아이디")
    @Permission(role = Permission.PermissionRole.USER)
    @PostMapping("/{id}/team")
    public ResponseEntity<List<String>> joinTeam(@PathVariable("id") Long id, @RequestBody Team team) {

        return new ResponseEntity<>(userService.joinTeam(id, team.getName()), HttpStatus.OK);
    }

    @ApiOperation(value = "팀 탈퇴")
    @ApiImplicitParam(name = "id", value = "유저 식별 아이디")
    @Permission(role = Permission.PermissionRole.USER)
    @DeleteMapping("/{id}/team")
    public ResponseEntity<User> leaveTeam(@PathVariable("id") Long id, @RequestBody Team team) {

        return new ResponseEntity<>(userService.leaveTeam(id, team.getName()), HttpStatus.NO_CONTENT);
    }

}
