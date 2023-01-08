package org.example.service;

import org.example.domain.User;
import org.example.dto.token.JwtToken;
import org.example.dto.user.RequestLogin;

import java.util.List;

public interface UserService {

    User createAdmin(User user);

    User createUser(User user);

    JwtToken login(RequestLogin requestLogin);

    void deleteUser(Long id);

    List<User> getUser();

    User getInformation(Long id);

    User updateUser(Long id, User user);

    User joinTeam(Long id, String teamName);

    User leaveTeam(Long id, String teamName);

}
