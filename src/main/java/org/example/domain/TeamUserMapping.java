package org.example.domain;

public class TeamUserMapping extends BaseEntity{

    private Long id;

    private Long userId;

    private Long teamId;

    public Long getId() {

        return id;

    }

    public void setId(Long id) {

        this.id = id;

    }

    public Long getUserId() {

        return userId;

    }

    public void setUserId(Long userId) {

        this.userId = userId;

    }

    public Long getTeamId() {

        return teamId;

    }

    public void setTeamId(Long teamId) {

        this.teamId = teamId;

    }

}

