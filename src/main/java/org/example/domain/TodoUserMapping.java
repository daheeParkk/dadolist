package org.example.domain;

import lombok.Data;

@Data
public class TodoUserMapping extends BaseEntity{

    private Long id;

    private Long todoId;

    private Long userId;

}

