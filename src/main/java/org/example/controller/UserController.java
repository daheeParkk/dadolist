package org.example.controller;

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

    @PostMapping
    public void createUser(@RequestBody User user){

        userService.createUser(user);

    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody RequestLogin requestLogin){

        return userService.login(requestLogin);

    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){

        userService.deleteUser(id);

    }

    @GetMapping
    public List<User> getUser(){

        return userService.getUser();

    }

    @GetMapping("/{id}")
    public User getInformation(@PathVariable("id") Long id){

        return userService.getInformation(id);

    }

    @PatchMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody RequestUser requestUser){

        return userService.updateUser(id, requestUser);

    }

    @PostMapping("/{id}/team")
    public User joinTeam(@PathVariable("id") Long id, @RequestBody String teamName){

        return userService.joinTeam(id, teamName);

    }

    @DeleteMapping("/{id}/team")
    public User leaveTeam(@PathVariable("id") Long id, @RequestBody String teamName) {

        return userService.leaveTeam(id, teamName);

    }

}
