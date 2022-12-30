package org.example.dto.todo;

public class GetTodoList {

    private Long userId;

    private String month;

    private String days;

    private String category;

    public Long getUserId() {

        return userId;

    }

    public void setUserId(Long userId) {

        this.userId = userId;

    }

    public String getMonth() {

        return month;

    }

    public void setMonth(String month) {

        this.month = month;

    }

    public String getDays() {

        return days;

    }

    public void setDays(String days) {

        this.days = days;

    }

    public String getCategory() {

        return category;

    }

    public void setCategory(String category) {

        this.category = category;

    }

}
