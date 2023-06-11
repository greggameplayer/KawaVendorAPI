package com.kawa.service.dto.request;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

public class ProductRequestDTO implements Serializable {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRequestDTO that = (ProductRequestDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ProductRequestDTO.class.getSimpleName() + "{", "}").add("id='" + id + "'").toString();
    }
}
