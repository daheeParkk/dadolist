package org.example.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Token extends BaseEntity {

    private Long id;

    private String userId;

    private String refreshToken;

}
