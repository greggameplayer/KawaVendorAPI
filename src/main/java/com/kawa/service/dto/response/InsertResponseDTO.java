package com.kawa.service.dto.response;

import java.util.Objects;

public class InsertResponseDTO {

    private String insertedId;

    public String getInsertedId() {
        return insertedId;
    }

    public void setInsertedId(String insertedId) {
        this.insertedId = insertedId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsertResponseDTO that = (InsertResponseDTO) o;
        return Objects.equals(insertedId, that.insertedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(insertedId);
    }

    @Override
    public String toString() {
        return "ProductInsertResponseDTO{" + "insertedId='" + insertedId + '\'' + '}';
    }
}
