package com.kawa.domain.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class ProductWithoutId {

    protected Date createdAt;
    protected String name;
    protected int stock;
    protected double detailsPrice;
    protected String detailsDescription;
    protected String detailsColor;

    @JsonProperty("details")
    protected void unpackNested(Map<String, Object> details) {
        this.detailsPrice = (double) details.get("price");
        this.detailsDescription = (String) details.get("description");
        this.detailsColor = (String) details.get("color");
    }

    @SuppressWarnings("unchecked")
    public void unpackNestedMap(Map<String, Object> map) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        this.createdAt = formatter.parse((String) map.get("createdAt"));
        this.name = (String) map.get("name");
        this.stock = Integer.parseInt((String) map.get("stock"));
        Map<String, Object> details = (Map<String, Object>) map.get("details");
        unpackNested(details);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public ProductWithoutId createdAt(Date createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public ProductWithoutId name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public ProductWithoutId stock(int stock) {
        this.setStock(stock);
        return this;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getDetailsPrice() {
        return detailsPrice;
    }

    public ProductWithoutId detailsPrice(double detailsPrice) {
        this.setDetailsPrice(detailsPrice);
        return this;
    }

    public void setDetailsPrice(double detailsPrice) {
        this.detailsPrice = detailsPrice;
    }

    public String getDetailsDescription() {
        return detailsDescription;
    }

    public ProductWithoutId detailsDescription(String detailsDescription) {
        this.setDetailsDescription(detailsDescription);
        return this;
    }

    public void setDetailsDescription(String detailsDescription) {
        this.detailsDescription = detailsDescription;
    }

    public String getDetailsColor() {
        return detailsColor;
    }

    public ProductWithoutId detailsColor(String detailsColor) {
        this.setDetailsColor(detailsColor);
        return this;
    }

    public void setDetailsColor(String detailsColor) {
        this.detailsColor = detailsColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductWithoutId that = (ProductWithoutId) o;
        return (
            stock == that.stock &&
            Double.compare(that.detailsPrice, detailsPrice) == 0 &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(name, that.name) &&
            Objects.equals(detailsDescription, that.detailsDescription) &&
            Objects.equals(detailsColor, that.detailsColor)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdAt, name, stock, detailsPrice, detailsDescription, detailsColor);
    }

    @Override
    public String toString() {
        return (
            "ProductWithoutId{" +
            "createdAt=" +
            createdAt +
            ", name='" +
            name +
            '\'' +
            ", stock=" +
            stock +
            ", detailsPrice=" +
            detailsPrice +
            ", detailsDescription='" +
            detailsDescription +
            '\'' +
            ", detailsColor='" +
            detailsColor +
            '\'' +
            '}'
        );
    }
}
