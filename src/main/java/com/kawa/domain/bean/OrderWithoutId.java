package com.kawa.domain.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderWithoutId {

    protected final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    protected Date createdAt;

    protected String customerId;

    protected List<Product> products;

    public OrderWithoutId() {
        dateFormat.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
    }

    @SuppressWarnings("unchecked")
    public void unpackNestedMap(Map<String, Object> map) throws ParseException {
        this.createdAt = dateFormat.parse((String) map.get("createdAt"));
        this.customerId = (String) map.get("customerId");
        this.products = (List<Product>) map.get("products");
    }

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    public Date getCreatedAt() {
        return createdAt;
    }

    public OrderWithoutId createdAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCustomerId() {
        return customerId;
    }

    public OrderWithoutId customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public OrderWithoutId products(List<Product> products) {
        this.products = products;
        return this;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @JsonIgnore
    public boolean isNull() {
        return (createdAt == null && customerId == null && products == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderWithoutId)) return false;
        OrderWithoutId that = (OrderWithoutId) o;
        return (
            Objects.equals(dateFormat, that.dateFormat) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(customerId, that.customerId) &&
            Objects.equals(products, that.products)
        );
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return (
            "OrderWithoutId{" +
            "dateFormat=" +
            dateFormat +
            ", createdAt=" +
            createdAt +
            ", customerId='" +
            customerId +
            '\'' +
            ", products=" +
            products +
            '}'
        );
    }
}
