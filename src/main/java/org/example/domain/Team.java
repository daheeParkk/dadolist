package org.example.domain;

import lombok.Data;
import org.example.dto.team.RequestTeam;

@Data
public class Team extends BaseEntity{

    private Long id;

    private String name;

    public void update(RequestTeam requestTeam){

        this.name = requestTeam.getName();

    }

}
