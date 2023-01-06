package org.example.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Todo extends BaseEntity {

    private Long id;

    private String content;

    private String deadline;

    private Long categoryId;

    private String category;

    private Long teamId;

    private String team;

}
