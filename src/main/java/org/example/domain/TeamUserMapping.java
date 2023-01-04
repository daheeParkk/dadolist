package org.example.domain;

import lombok.Data;

@Data
public class TeamUserMapping extends BaseEntity{

    private Long id;

    private Long userId;

    private Long teamId;

}

