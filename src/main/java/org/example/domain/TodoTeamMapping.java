package org.example.domain;

public class TodoTeamMapping extends BaseEntity{

    private Long id;

    private Long todoId;

    private Long teamId;

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

    public Long getTeamId() {

        return teamId;

    }

    public void setTeamId(Long teamId) {

        this.teamId = teamId;

    }

}
