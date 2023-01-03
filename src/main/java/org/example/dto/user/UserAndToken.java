package org.example.dto.user;

import org.example.domain.User;

public class UserAndToken {

    private User user;

    private String accessToke;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccessToke() {
        return accessToke;
    }

    public void setAccessToke(String accessToke) {
        this.accessToke = accessToke;
    }
}
