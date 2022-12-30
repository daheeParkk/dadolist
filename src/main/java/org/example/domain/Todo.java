package org.example.domain;

import org.example.dto.todo.RequestTodo;

public class Todo extends BaseEntity {

    private Long id;

    private String content;

    private String deadline;

    private Long categoryId;
    private String category;

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

    public void update(RequestTodo requestTodo){

        this.content = requestTodo.getContent();

        this.deadline = requestTodo.getDeadline();

        this.categoryId = requestTodo.getCategoryId();

    }

}
