package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TodoUserMapping extends BaseEntity{

    private Long id;

    private Long todoId;

    private Long userId;

}

