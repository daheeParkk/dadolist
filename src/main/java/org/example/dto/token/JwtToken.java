package org.example.dto.token;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtToken {

    private String accessToken;

    private String refreshToken;

}
