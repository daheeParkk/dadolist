package org.example.domain;

import java.sql.Timestamp;

public class BaseEntity {

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Boolean isDeleted = false;

    public Timestamp getCreatedAt() {

        return createdAt;

    }

    public void setCreatedAt(Timestamp createdAt) {

        this.createdAt = createdAt;

    }

    public Timestamp getUpdatedAt() {

        return updatedAt;

    }

    public void setUpdatedAt(Timestamp updatedAt) {

        this.updatedAt = updatedAt;

    }

    public Boolean getIsDeleted() {

        return isDeleted;

    }

    public void setIsDeleted(Boolean deleted) {

        isDeleted = deleted;

    }

}

