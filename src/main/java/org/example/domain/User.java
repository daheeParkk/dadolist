package org.example.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @ApiModelProperty(example = "식별 아이디")
    private Long id;

    @ApiModelProperty(example = "이름")
    private String name;

    @ApiModelProperty(example = "유저 아이디")
    private String userId;

    @ApiModelProperty(example = "닉네임")
    private String nickname;

    @ApiModelProperty(example = "비밀번호")
    private String password;

    @ApiModelProperty(example = "이메일")
    private String email;

    @ApiModelProperty(example = "권한")
    private Long authority;

}