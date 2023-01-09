package org.example.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Todo extends BaseEntity {

    @ApiModelProperty(example = "식별 아이디")
    private Long id;

    @ApiModelProperty(example = "todo 글")
    private String content;

    @ApiModelProperty(example = "마감일")
    private String deadline;

    @ApiModelProperty(example = "카테고리 아이디")
    private Long categoryId;

    @ApiModelProperty(example = "카테고리")
    private String category;

    @ApiModelProperty(example = "팀 아이디")
    private Long teamId;

    @ApiModelProperty(example = "팀")
    private String team;

}
