package org.example.repository;

import org.apache.ibatis.annotations.Param;
import org.example.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    void createAdmin(@Param("id") Long id,@Param("checkAdmin") Boolean checkAdmin);

    void createUser(User user);

    int countUserIdByUserId(String userId);

    int selectNicknameByNickname(String nickname);

    User selectUserByUserId(String userId);

    void softDelete(@Param("isDeleted") Boolean isDeleted, @Param("id") Long id);

    void updateUser(User user);

    List<User> getUser();

    User selectUser(Long id);

}
