package org.example.domain;

import lombok.Data;
import org.example.dto.todo.RequestTodo;

@Data
public class Todo extends BaseEntity {

    private Long id;

    private String content;

    private String deadline;

    private Long categoryId;

    private String category;

    private Long teamId;

    private String team;

    public void update(RequestTodo requestTodo){

        this.content = requestTodo.getContent();

        this.deadline = requestTodo.getDeadline();

        this.categoryId = requestTodo.getCategoryId();

    }

}
