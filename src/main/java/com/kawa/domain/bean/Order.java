package com.kawa.domain.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.ParseException;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class Order extends OrderWithoutId {

    public Order() {
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

    public Order id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    @Override
    public boolean isNull() {
        return (super.isNull() && this.id == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public String toString() {
        String createdAtStr = (this.createdAt == null) ? null : dateFormat.format(this.createdAt);

        return new StringJoiner(", ", Order.class.getSimpleName() + "{", "}")
            .add("id='" + id + "'")
            .add("createdAt='" + createdAtStr + "'")
            .add("customerId='" + getCustomerId() + "'")
            .add("products=" + getProducts())
            .toString();
    }
}
