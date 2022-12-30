package org.example.domain;

import org.example.dto.team.RequestTeam;

public class Team extends BaseEntity{

    private Long id;

    private String name;

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

    public void update(RequestTeam requestTeam){

        this.name = requestTeam.getName();

    }

}
