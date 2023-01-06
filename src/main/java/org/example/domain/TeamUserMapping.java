package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TeamUserMapping extends BaseEntity{

    private Long id;

    private Long userId;

    private Long teamId;

}

