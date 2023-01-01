package org.example.domain;

import org.example.dto.todo.RequestTodo;

public class Todo extends BaseEntity {

    private Long id;

    private String content;

    private String deadline;

    private Long categoryId;

    private String category;

    private Long teamId;

    private String team;

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public void update(RequestTodo requestTodo){

        this.content = requestTodo.getContent();

        this.deadline = requestTodo.getDeadline();

        this.categoryId = requestTodo.getCategoryId();

    }

}
