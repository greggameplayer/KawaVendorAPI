package com.kawa.service.dto.response.mongo;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class FindAllMongoResponseDTO<T> {

    protected List<T> documents;

    public List<T> getDocuments() {
        return Collections.unmodifiableList(documents);
    }

    public void setDocuments(List<T> documents) {
        this.documents = documents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindAllMongoResponseDTO<?> that = (FindAllMongoResponseDTO<?>) o;
        return Objects.equals(documents, that.documents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documents);
    }

    @Override
    public String toString() {
        return "FindAllMongoResponseDTO{" + "documents=" + documents + '}';
    }
}
