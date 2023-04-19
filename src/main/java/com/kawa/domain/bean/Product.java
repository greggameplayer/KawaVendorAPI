package com.kawa.domain.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.ParseException;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class Product extends ProductWithoutId {

    @JsonProperty("_id")
    private String id;

    @Override
    @SuppressWarnings("unchecked")
    public void unpackNestedMap(Map<String, Object> map) throws ParseException {
        super.unpackNestedMap(map);
        Map<String, Object> details = (Map<String, Object>) map.get("details");
        this.id = (String) details.get("_id");
    }

    public String getId() {
        return id;
    }

    public Product id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
            .add("id='" + id + "'")
            .add("createdAt=" + getCreatedAt())
            .add("name='" + getName() + "'")
            .add("stock=" + getStock())
            .add("detailsPrice=" + getDetailsPrice())
            .add("detailsDescription='" + getDetailsDescription() + "'")
            .add("detailsColor='" + getDetailsColor() + "'")
            .toString();
    }
}
