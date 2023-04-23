package com.kawa.service.dto.response.mongo;

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

    @Override
    protected void unpackNested(Map<String, Object> document) throws ParseException {
        Product product = new Product();
        product.unpackNestedMap(document);
        this.documentId = product.getId();
        this.documentCreatedAt = product.getCreatedAt();
        this.documentName = product.getName();
        this.documentStock = product.getStock();
        this.documentDetailsPrice = product.getDetailsPrice();
        this.documentDetailsDescription = product.getDetailsDescription();
        this.documentDetailsColor = product.getDetailsColor();
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Date getDocumentCreatedAt() {
        return documentCreatedAt;
    }

    public void setDocumentCreatedAt(Date documentCreatedAt) {
        this.documentCreatedAt = documentCreatedAt;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public int getDocumentStock() {
        return documentStock;
    }

    public void setDocumentStock(int documentStock) {
        this.documentStock = documentStock;
    }

    public double getDocumentDetailsPrice() {
        return documentDetailsPrice;
    }

    public void setDocumentDetailsPrice(double documentDetailsPrice) {
        this.documentDetailsPrice = documentDetailsPrice;
    }

    public String getDocumentDetailsDescription() {
        return documentDetailsDescription;
    }

    public void setDocumentDetailsDescription(String documentDetailsDescription) {
        this.documentDetailsDescription = documentDetailsDescription;
    }

    public String getDocumentDetailsColor() {
        return documentDetailsColor;
    }

    public void setDocumentDetailsColor(String documentDetailsColor) {
        this.documentDetailsColor = documentDetailsColor;
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
