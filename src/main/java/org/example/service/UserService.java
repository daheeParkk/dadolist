package org.example.service;

import org.example.domain.User;
import org.example.dto.token.JwtToken;
import org.example.dto.user.UserVerification;

import java.util.List;

public interface UserService {

    User createAdmin(User user);

    User createUser(User user);

    JwtToken login(UserVerification userVerification);

    void logout(Long id);

    void deleteUser(Long id);

    List<User> getUser();

    User getInformation(Long id);

    User updateUser(Long id, User user);

    List<String> joinTeam(Long id, String teamName);

    User leaveTeam(Long id, String teamName);

}
