package com.kawa.service.dto.response.mongo;

import java.util.Objects;

public class InsertMongoResponseDTO {

    protected String insertedId;

    public String getInsertedId() {
        return insertedId;
    }

    public void setInsertedId(String insertedId) {
        this.insertedId = insertedId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsertMongoResponseDTO)) return false;
        InsertMongoResponseDTO that = (InsertMongoResponseDTO) o;
        return Objects.equals(insertedId, that.insertedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(insertedId);
    }

    @Override
    public String toString() {
        return "InsertMongoResponseDTO{" + "insertedId='" + insertedId + '\'' + '}';
    }
}
