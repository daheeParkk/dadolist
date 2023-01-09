package org.example.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity {

    @ApiModelProperty(example = "식별 아이디")
    private Long id;

    @ApiModelProperty(example = "카테고리 이름")
    private String content;

}
