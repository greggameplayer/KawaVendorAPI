package com.kawa.service.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.Objects;

public class ProductInsertRequestDTO {

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

    public String getName() {
        return name;
    }

    public ProductInsertRequestDTO name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public ProductInsertRequestDTO stock(int stock) {
        this.setStock(stock);
        return this;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getDetailsPrice() {
        return detailsPrice;
    }

    public ProductInsertRequestDTO detailsPrice(double detailsPrice) {
        this.setDetailsPrice(detailsPrice);
        return this;
    }

    public void setDetailsPrice(double detailsPrice) {
        this.detailsPrice = detailsPrice;
    }

    public String getDetailsDescription() {
        return detailsDescription;
    }

    public ProductInsertRequestDTO detailsDescription(String detailsDescription) {
        this.setDetailsDescription(detailsDescription);
        return this;
    }

    public void setDetailsDescription(String detailsDescription) {
        this.detailsDescription = detailsDescription;
    }

    public String getDetailsColor() {
        return detailsColor;
    }

    public ProductInsertRequestDTO detailsColor(String detailsColor) {
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
        ProductInsertRequestDTO that = (ProductInsertRequestDTO) o;
        return (
            stock == that.stock &&
            Double.compare(that.detailsPrice, detailsPrice) == 0 &&
            Objects.equals(name, that.name) &&
            Objects.equals(detailsDescription, that.detailsDescription) &&
            Objects.equals(detailsColor, that.detailsColor)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, stock, detailsPrice, detailsDescription, detailsColor);
    }

    @Override
    public String toString() {
        return (
            "ProductInsertRequestDTO{" +
            "name='" +
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
