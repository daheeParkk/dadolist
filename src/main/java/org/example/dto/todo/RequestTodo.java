package org.example.dto.todo;

public class RequestTodo {

    private String content;

    private String deadline;

    private Long categoryId;

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

}
