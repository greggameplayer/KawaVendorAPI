package com.kawa.service.dto.response.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kawa.domain.bean.Product;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

public class ProductFindOneMongoResponseDTO {

    private String documentId;
    private Date documentCreatedAt;
    private String documentName;
    private int documentStock;
    private double documentDetailsPrice;
    private String documentDetailsDescription;
    private String documentDetailsColor;

    @JsonProperty("document")
    private void unpackNested(Map<String, Object> document) throws ParseException {
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
}
