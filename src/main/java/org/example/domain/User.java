package org.example.domain;

import org.example.dto.user.RequestUser;

public class User extends BaseEntity {

    private Long id;

    private String name;

    private String userId;

    private String nickname;

    private String password;

    private String email;

    public Long getId() {

        return id;

    }

    public void setId(Long id) {

        this.id = id;

    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public String getUserId() {

        return userId;

    }

    public void setUserId(String userId) {

        this.userId = userId;

    }

    public String getNickname() {

        return nickname;

    }

    public void setNickname(String nickname) {

        this.nickname = nickname;

    }

    public String getPassword() {

        return password;

    }

    public void setPassword(String password) {

        this.password = password;

    }

    public String getEmail() {

        return email;

    }

    public void setEmail(String email) {

        this.email = email;

    }

    public void update(RequestUser requestUser){

        this.name = requestUser.getName();
        this.nickname = requestUser.getNickname();
        this.userId = requestUser.getUserId();
        this.password = requestUser.getPassword();
        this.email = requestUser.getEmail();

    }

}