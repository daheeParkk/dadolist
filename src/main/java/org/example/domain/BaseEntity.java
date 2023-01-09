package org.example.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class BaseEntity {

    @ApiModelProperty(example = "생성된 날짜")
    private Timestamp createdAt;

    @ApiModelProperty(example = "업데이트된 날짜")
    private Timestamp updatedAt;

    @ApiModelProperty(example = "삭제 여부")
    private Boolean isDeleted = false;

}

