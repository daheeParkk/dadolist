package org.example.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BaseEntity {

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Boolean isDeleted = false;

}

