package org.example.domain;

import lombok.Data;
import org.example.dto.user.RequestUser;

@Data
public class User extends BaseEntity {

    private Long id;

    private String name;

    private String userId;

    private String nickname;

    private String password;

    private String email;

    private Long authority;

    public void update(RequestUser requestUser){

        this.name = requestUser.getName();
        this.nickname = requestUser.getNickname();
        this.userId = requestUser.getUserId();
        this.password = requestUser.getPassword();
        this.email = requestUser.getEmail();

    }

}