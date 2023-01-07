package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class BaseEntity {

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Boolean isDeleted = false;

}

