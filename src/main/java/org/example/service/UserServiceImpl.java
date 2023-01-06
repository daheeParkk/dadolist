package org.example.service;

import org.example.domain.Team;
import org.example.domain.User;
import org.example.dto.token.JwtToken;
import org.example.dto.user.RequestLogin;
import org.example.dto.user.RequestUser;
import org.example.exception.DoesNotExistException;
import org.example.exception.DuplicateException;
import org.example.repository.TeamMapper;
import org.example.repository.TeamUserMapper;
import org.example.repository.TokenMapper;
import org.example.repository.UserMapper;
import org.example.util.BcryptUtil;
import org.example.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;

    private final BcryptUtil bcryptUtil;

    private final JWTUtil jwtUtil;

    private final TeamMapper teamMapper;

    private final TeamUserMapper teamUserMapper;

    private final TokenMapper tokenMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, BcryptUtil bcryptUtil, JWTUtil jwtUtil, TeamMapper teamMapper, TeamUserMapper teamUserMapper,
                           TokenMapper tokenMapper){

        this.userMapper = userMapper;
        this.bcryptUtil = bcryptUtil;
        this.jwtUtil = jwtUtil;
        this.teamMapper = teamMapper;
        this.teamUserMapper = teamUserMapper;
        this.tokenMapper = tokenMapper;

    }

    @Transactional
    @Override
    public User createAdmin(User user) {

        if(userMapper.countUserIdByUserId(user.getUserId()) == 1){
            throw new DuplicateException("This ID exists.");
        } else if (userMapper.countNicknameByNickname(user.getNickname()) == 1) {
            throw new DuplicateException("This Nickname exists.");
        } else {
            String encryptedPassword = bcryptUtil.encrypt(user.getPassword());
            user.setPassword(encryptedPassword);
            userMapper.createUser(user);
            userMapper.createAdmin(user.getId(), true);
            return userMapper.selectUser(user.getId());
        }

    }

    @Override
    public User createUser(User user) {

        if(userMapper.countUserIdByUserId(user.getUserId()) == 1){
            throw new DuplicateException("This ID exists.");
        } else if (userMapper.countNicknameByNickname(user.getNickname()) == 1) {
            throw new DuplicateException("This Nickname exists.");
        } else {
            String encryptedPassword = bcryptUtil.encrypt(user.getPassword());
            user.setPassword(encryptedPassword);
            userMapper.createUser(user);
            return userMapper.selectUser(user.getId());
        }

    }

    @Override
    public JwtToken login(RequestLogin requestLogin) {

        User info = userMapper.selectUserByUserId(requestLogin.getUserId());

        if(info == null){
            throw new DoesNotExistException("User not found");
        }

        bcryptUtil.checkPassword(requestLogin.getPassword(), info.getPassword());

        JwtToken token = jwtUtil.crateToken(info.getId(), true, true);
        tokenMapper.createRefreshToken(info.getId(), token.getRefreshToken());

        return token;

    }

    @Override
    public void deleteUser(Long id) {

        userMapper.softDelete(true, id);

    }

    @Override
    public List<User> getUser() {

        return userMapper.getUser();

    }

    @Override
    public User getInformation(Long id) {

        return userMapper.selectUser(id);

    }

    @Override
    public User updateUser(Long id, RequestUser requestUser) {

        User user = User.builder()
                .id(id)
                .name(requestUser.getName())
                .nickname(requestUser.getNickname())
                .userId(requestUser.getUserId())
                .password(requestUser.getPassword())
                .email(requestUser.getEmail())
                .build();

        userMapper.updateUser(user);
        return userMapper.selectUser(id);

    }

    @Override
    public User joinTeam(Long id, String teamName) {

        Team info = teamMapper.selectTeamByTeamName(teamName);
        System.out.println(info);
        if (info == null){
            throw new DoesNotExistException("Team not found");
        }
        Long teamId = info.getId();
        teamUserMapper.joinTeam(id, teamId);
        return userMapper.selectUser(id);

    }

    @Override
    public User leaveTeam(Long id, String team) {

        Team info = teamMapper.selectTeamByTeamName(team);
        if (info == null) {
            throw new DoesNotExistException("Team not found");
        }
        Long teamId = info.getId();
        teamUserMapper.softDelete(true, teamId);
        return userMapper.selectUser(id);

    }

}
