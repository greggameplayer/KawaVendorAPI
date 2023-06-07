package com.kawa.service.dto.request.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kawa.domain.bean.ProductWithoutId;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class ProductInsertMongoRequestDTO extends InsertMongoRequestDTO {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    private Date documentCreatedAt;
    private String documentName;
    private int documentStock;
    private double documentDetailsPrice;
    private String documentDetailsDescription;
    private String documentDetailsColor;

    private final ProductWithoutId document = new ProductWithoutId();

    public ProductInsertMongoRequestDTO() {
        this.collection = "products";
        dateFormat.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
    }

    @JsonIgnore
    public Date getDocumentCreatedAt() {
        return documentCreatedAt;
    }

    public void setDocumentCreatedAt(Date documentCreatedAt) {
        this.documentCreatedAt = documentCreatedAt;
    }

    @JsonIgnore
    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    @JsonIgnore
    public int getDocumentStock() {
        return documentStock;
    }

    public void setDocumentStock(int documentStock) {
        this.documentStock = documentStock;
    }

    @JsonIgnore
    public double getDocumentDetailsPrice() {
        return documentDetailsPrice;
    }

    public void setDocumentDetailsPrice(double documentDetailsPrice) {
        this.documentDetailsPrice = documentDetailsPrice;
    }

    @JsonIgnore
    public String getDocumentDetailsDescription() {
        return documentDetailsDescription;
    }

    public void setDocumentDetailsDescription(String documentDetailsDescription) {
        this.documentDetailsDescription = documentDetailsDescription;
    }

    @JsonIgnore
    public String getDocumentDetailsColor() {
        return documentDetailsColor;
    }

    public void setDocumentDetailsColor(String documentDetailsColor) {
        this.documentDetailsColor = documentDetailsColor;
    }

    @JsonProperty("document")
    private void unpackNested(Map<String, Object> document) throws ParseException {
        ProductWithoutId product = new ProductWithoutId();
        product.unpackNestedMap(document);
        documentCreatedAt = product.getCreatedAt();
        documentName = product.getName();
        documentStock = product.getStock();
        documentDetailsPrice = product.getDetailsPrice();
        documentDetailsDescription = product.getDetailsDescription();
        documentDetailsColor = product.getDetailsColor();
    }

    @JsonIncludeProperties({ "createdAt", "name", "stock", "details" })
    public ProductWithoutId getDocument() {
        document.setCreatedAt(documentCreatedAt);
        document.setName(documentName);
        document.setStock(documentStock);
        document.setDetailsPrice(documentDetailsPrice);
        document.setDetailsDescription(documentDetailsDescription);
        document.setDetailsColor(documentDetailsColor);
        return document;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductInsertMongoRequestDTO)) return false;
        if (!super.equals(o)) return false;
        ProductInsertMongoRequestDTO that = (ProductInsertMongoRequestDTO) o;
        return (
            documentStock == that.documentStock &&
            Double.compare(that.documentDetailsPrice, documentDetailsPrice) == 0 &&
            Objects.equals(documentCreatedAt, that.documentCreatedAt) &&
            Objects.equals(documentName, that.documentName) &&
            Objects.equals(documentDetailsDescription, that.documentDetailsDescription) &&
            Objects.equals(documentDetailsColor, that.documentDetailsColor)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            documentCreatedAt,
            documentName,
            documentStock,
            documentDetailsPrice,
            documentDetailsDescription,
            documentDetailsColor
        );
    }

    @Override
    public String toString() {
        return (
            "ProductInsertMongoRequestDTO{" +
            "dataSource='" +
            dataSource +
            '\'' +
            ", database='" +
            database +
            '\'' +
            ", collection='" +
            collection +
            '\'' +
            ", documentCreatedAt='" +
            dateFormat.format(documentCreatedAt) +
            "', documentName='" +
            documentName +
            '\'' +
            ", documentStock=" +
            documentStock +
            ", documentDetailsPrice=" +
            documentDetailsPrice +
            ", documentDetailsDescription='" +
            documentDetailsDescription +
            '\'' +
            ", documentDetailsColor='" +
            documentDetailsColor +
            '\'' +
            '}'
        );
    }
}
