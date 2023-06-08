package com.kawa.service.dto.response.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kawa.domain.bean.Product;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class ProductFindOneMongoResponseDTO extends FindOneMongoResponseDTO {

    private String documentId;
    private Date documentCreatedAt;
    private String documentName;
    private int documentStock;
    private double documentDetailsPrice;
    private String documentDetailsDescription;
    private String documentDetailsColor;

    private final Product document = new Product();

    private void setDocumentValues(Product innerProduct) {
        this.documentId = innerProduct.getId();
        this.documentCreatedAt = innerProduct.getCreatedAt();
        this.documentName = innerProduct.getName();
        this.documentStock = innerProduct.getStock();
        this.documentDetailsPrice = innerProduct.getDetailsPrice();
        this.documentDetailsDescription = innerProduct.getDetailsDescription();
        this.documentDetailsColor = innerProduct.getDetailsColor();
    }

    @JsonProperty("document")
    @Override
    protected void unpackNested(Map<String, Object> document) throws ParseException {
        if (document != null) {
            Product product = new Product();
            product.unpackNestedMap(document);
            setDocumentValues(product);
        }
    }

    @JsonIgnore
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
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
    @JsonIncludeProperties({ "createdAt", "name", "stock", "details", "_id" })
    public Product getDocument() {
        document.setId(documentId);
        document.setCreatedAt(documentCreatedAt);
        document.setName(documentName);
        document.setStock(documentStock);
        document.setDetailsPrice(documentDetailsPrice);
        document.setDetailsDescription(documentDetailsDescription);
        document.setDetailsColor(documentDetailsColor);
        return document;
    }

    public void setDocument(Product document) {
        setDocumentValues(document);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductFindOneMongoResponseDTO)) return false;
        ProductFindOneMongoResponseDTO that = (ProductFindOneMongoResponseDTO) o;
        return (
            documentStock == that.documentStock &&
            Double.compare(that.documentDetailsPrice, documentDetailsPrice) == 0 &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(documentCreatedAt, that.documentCreatedAt) &&
            Objects.equals(documentName, that.documentName) &&
            Objects.equals(documentDetailsDescription, that.documentDetailsDescription) &&
            Objects.equals(documentDetailsColor, that.documentDetailsColor)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            documentId,
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
            "ProductFindOneMongoResponseDTO{" +
            "documentId='" +
            documentId +
            '\'' +
            ", documentCreatedAt=" +
            documentCreatedAt +
            ", documentName='" +
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
            "}"
        );
    }
}
