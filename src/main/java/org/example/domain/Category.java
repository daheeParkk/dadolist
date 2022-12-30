package org.example.domain;

import org.example.dto.category.RequestCategory;

public class Category extends BaseEntity {

    private Long id;
    private String content;

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

    public void update(RequestCategory requestCategory) {

        this.content = requestCategory.getContent();

    }

}
