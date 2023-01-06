package org.example.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Category extends BaseEntity {

    private Long id;

    private String content;

}
