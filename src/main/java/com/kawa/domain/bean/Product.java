package com.kawa.domain.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.ParseException;
import java.util.Map;
import java.util.StringJoiner;

public class Product extends ProductWithoutId {

    public Product() {
        super();
    }

    @JsonProperty("_id")
    private String id;

    @Override
    public void unpackNestedMap(Map<String, Object> map) throws ParseException {
        super.unpackNestedMap(map);
        this.id = (String) map.get("_id");
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
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    @JsonIgnore
    public boolean isNull() {
        return this.id == null && super.isNull();
    }

    @Override
    public String toString() {
        String createdAtStr = (this.createdAt == null) ? null : dateFormat.format(this.createdAt);

        return new StringJoiner(", ", Product.class.getSimpleName() + "{", "}")
            .add("id='" + id + "'")
            .add("createdAt='" + createdAtStr + "'")
            .add("name='" + getName() + "'")
            .add("stock=" + getStock())
            .add("detailsPrice=" + getDetailsPrice())
            .add("detailsDescription='" + getDetailsDescription() + "'")
            .add("detailsColor='" + getDetailsColor() + "'")
            .toString();
    }
}
