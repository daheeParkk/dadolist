package org.example.controller;

import org.example.annotation.NoAuth;
import org.example.annotation.Permission;
import org.example.domain.User;
import org.example.dto.user.RequestLogin;
import org.example.dto.user.RequestUser;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final HttpServletResponse httpServletResponse;

    @Autowired
    public UserController(UserService userService, HttpServletResponse httpServletResponse){

        this.userService = userService;
        this.httpServletResponse = httpServletResponse;

    }

    @PostMapping("/admin")
    public void createAdmin(@RequestBody User user) {
        userService.createAdmin(user);
    }

    @NoAuth
    @PostMapping
    public void createUser(@RequestBody User user){

        userService.createUser(user);

    }

    @NoAuth
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody RequestLogin requestLogin){

        return userService.login(requestLogin, httpServletResponse);

    }

    @Permission(role = Permission.PermissionRole.USER)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){

        userService.deleteUser(id);

    }

    @NoAuth
    @GetMapping
    public List<User> getUser(){

        return userService.getUser();

    }

    @Permission(role = Permission.PermissionRole.USER)
    @GetMapping("/{id}")
    public User getInformation(@PathVariable("id") Long id){

        return userService.getInformation(id);

    }

    @Permission(role = Permission.PermissionRole.USER)
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody RequestUser requestUser){

        return userService.updateUser(id, requestUser);

    }

    @Permission(role = Permission.PermissionRole.USER)
    @PostMapping("/{id}/team")
    public User joinTeam(@PathVariable("id") Long id, @RequestBody String teamName){

        return userService.joinTeam(id, teamName);

    }

    @Permission(role = Permission.PermissionRole.USER)
    @DeleteMapping("/{id}/team")
    public User leaveTeam(@PathVariable("id") Long id, @RequestBody String teamName) {

        return userService.leaveTeam(id, teamName);

    }

}
