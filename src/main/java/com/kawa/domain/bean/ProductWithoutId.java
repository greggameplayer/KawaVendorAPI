package com.kawa.domain.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProductWithoutId {

    protected static final String DETAILS_PRICE = "price";

    protected static final String STOCK_IDX = "stock";

    protected final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    protected Date createdAt;
    protected String name;
    protected int stock;
    protected double detailsPrice;
    protected String detailsDescription;
    protected String detailsColor;

    protected final Map<String, Object> details = new HashMap<>();

    public ProductWithoutId() {
        dateFormat.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
    }

    @JsonProperty("details")
    protected void unpackNested(Map<String, Object> details) {
        if (details.get(DETAILS_PRICE) instanceof Double) {
            this.detailsPrice = (double) details.get(DETAILS_PRICE);
        } else {
            this.detailsPrice = (Integer) details.get(DETAILS_PRICE);
        }
        this.detailsDescription = (String) details.get("description");
        this.detailsColor = (String) details.get("color");
    }

    @SuppressWarnings("unchecked")
    public void unpackNestedMap(Map<String, Object> map) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        this.createdAt = formatter.parse((String) map.get("createdAt"));
        this.name = (String) map.get("name");
        if (map.get(STOCK_IDX) instanceof String) {
            this.stock = Integer.parseInt((String) map.get(STOCK_IDX));
        } else {
            this.stock = (Integer) map.get(STOCK_IDX);
        }
        Map<String, Object> nestedDetails = (Map<String, Object>) map.get("details");
        unpackNested(nestedDetails);
    }

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
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

    @JsonIgnore
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

    @JsonIgnore
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

    @JsonIgnore
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

    public Map<String, Object> getDetails() {
        this.details.put(DETAILS_PRICE, detailsPrice);
        this.details.put("description", detailsDescription);
        this.details.put("color", detailsColor);

        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductWithoutId)) {
            return false;
        }
        ProductWithoutId that = (ProductWithoutId) o;
        return (
            createdAt != null &&
            name != null &&
            detailsDescription != null &&
            detailsColor != null &&
            stock == that.stock &&
            Double.compare(that.detailsPrice, detailsPrice) == 0 &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(name, that.name) &&
            Objects.equals(detailsDescription, that.detailsDescription) &&
            Objects.equals(detailsColor, that.detailsColor)
        );
    }

    @JsonIgnore
    public boolean isNull() {
        return (createdAt == null && name == null && detailsDescription == null && detailsColor == null && stock == 0 && detailsPrice == 0);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        String createdAtStr = (this.createdAt == null) ? null : dateFormat.format(this.createdAt);

        return (
            "ProductWithoutId{" +
            "createdAt='" +
            createdAtStr +
            "', name='" +
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
