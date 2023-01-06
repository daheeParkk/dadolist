package org.example.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    private Long id;

    private String name;

    private String userId;

    private String nickname;

    private String password;

    private String email;

    private Long authority;

}