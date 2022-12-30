package org.example.domain;

public class TodoUserMapping extends BaseEntity{

    private Long id;

    private Long todoId;

    private Long userId;

    public Long getId() {

        return id;

    }

    public void setId(Long id) {

        this.id = id;

    }

    public Long getTodoId() {

        return todoId;

    }

    public void setTodoId(Long todoId) {

        this.todoId = todoId;

    }

    public Long getUserId() {

        return userId;

    }

    public void setUserId(Long userId) {

        this.userId = userId;

    }

}

