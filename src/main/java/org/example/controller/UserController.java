package org.example.controller;

import org.example.annotation.Permission;
import org.example.domain.User;
import org.example.dto.user.RequestLogin;
import org.example.dto.user.RequestUser;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){

        this.userService = userService;

    }

    @PostMapping("/admin")
    public void createAdmin(@RequestBody User user) {
        userService.createAdmin(user);
    }

    @PostMapping
    public void createUser(@RequestBody User user){

        userService.createUser(user);

    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody RequestLogin requestLogin){

        return userService.login(requestLogin);

    }

    @Permission(role = Permission.PermissionRole.USER)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){

        userService.deleteUser(id);

    }

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
