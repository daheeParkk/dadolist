package org.example.domain;

import lombok.Data;
import org.example.dto.category.RequestCategory;

@Data
public class Category extends BaseEntity {

    private Long id;
    private String content;

    public void update(RequestCategory requestCategory) {

        this.content = requestCategory.getContent();

    }

}
