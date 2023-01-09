package org.example.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Team extends BaseEntity{

    @ApiModelProperty(example = "식별 아이디")
    private Long id;

    @ApiModelProperty(example = "팀 이름")
    private String name;

    @ApiModelProperty(example = "유저 수")
    private Long numOfUsers;

}
