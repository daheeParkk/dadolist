package org.example.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Team extends BaseEntity{

    private Long id;

    private String teamName;

}
