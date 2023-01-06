package org.example.service;

import org.example.domain.User;
import org.example.dto.token.JwtToken;
import org.example.dto.user.RequestLogin;
import org.example.dto.user.RequestUser;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface UserService {

    User createAdmin(User user);

    User createUser(User user);

    JwtToken login(RequestLogin requestLogin);

    void deleteUser(Long id);

    List<User> getUser();

    User getInformation(Long id);

    User updateUser(Long id, RequestUser requestUser);

    User joinTeam(Long id, String teamName);

    User leaveTeam(Long id, String teamName);

}
