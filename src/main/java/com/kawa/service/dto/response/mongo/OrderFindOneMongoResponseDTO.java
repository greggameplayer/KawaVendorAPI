package com.kawa.service.dto.response.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kawa.domain.bean.Order;
import com.kawa.domain.bean.Product;
import java.text.ParseException;
import java.util.*;

public class OrderFindOneMongoResponseDTO extends FindOneMongoResponseDTO {

    private String documentId;

    private Date documentCreatedAt;

    private String documentCustomerId;

    private List<Product> documentProducts;

    private final Order document = new Order();

    private void setDocumentValues(Order innerOrder) {
        this.documentId = innerOrder.getId();
        this.documentCreatedAt = innerOrder.getCreatedAt();
        this.documentCustomerId = innerOrder.getCustomerId();
        this.documentProducts = innerOrder.getProducts();
    }

    @JsonProperty("document")
    @Override
    protected void unpackNested(Map<String, Object> document) throws ParseException {
        if (document != null) {
            Order order = new Order();
            order.unpackNestedMap(document);
            setDocumentValues(order);
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
    public String getDocumentCustomerId() {
        return documentCustomerId;
    }

    public void setDocumentCustomerId(String documentCustomerId) {
        this.documentCustomerId = documentCustomerId;
    }

    @JsonIgnore
    public List<Product> getDocumentProducts() {
        return Collections.unmodifiableList(documentProducts);
    }

    public void setDocumentProducts(List<Product> documentProducts) {
        this.documentProducts = documentProducts;
    }

    @JsonProperty("document")
    @JsonIncludeProperties({ "_id", "createdAt", "customerId", "products" })
    public Order getDocument() {
        document.setId(documentId);
        document.setCreatedAt(documentCreatedAt);
        document.setCustomerId(documentCustomerId);
        document.setProducts(documentProducts);
        return document;
    }

    public void setDocument(Order document) {
        setDocumentValues(document);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderFindOneMongoResponseDTO)) return false;
        OrderFindOneMongoResponseDTO that = (OrderFindOneMongoResponseDTO) o;
        return (
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(documentCreatedAt, that.documentCreatedAt) &&
            Objects.equals(documentCustomerId, that.documentCustomerId) &&
            Objects.equals(documentProducts, that.documentProducts)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId, documentCreatedAt, documentCustomerId, documentProducts);
    }

    @Override
    public String toString() {
        return (
            "OrderFindOneMongoResponseDTO{" +
            "documentId='" +
            documentId +
            '\'' +
            ", documentCreatedAt=" +
            documentCreatedAt +
            ", documentCustomerId='" +
            documentCustomerId +
            '\'' +
            ", documentProducts=" +
            documentProducts +
            '}'
        );
    }
}
