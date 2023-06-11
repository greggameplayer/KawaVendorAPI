package com.kawa.service.dto.request.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kawa.domain.bean.OrderWithoutId;
import com.kawa.domain.bean.Product;
import java.text.ParseException;
import java.util.*;

public class OrderInsertMongoRequestDTO extends InsertMongoRequestDTO {

    private Date documentCreatedAt;

    private String documentCustomerId;

    private List<Product> documentProducts;

    private final OrderWithoutId document = new OrderWithoutId();

    public OrderInsertMongoRequestDTO() {
        this.collection = "orders";
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
    private void unpackNested(Map<String, Object> document) throws ParseException {
        OrderWithoutId order = new OrderWithoutId();
        order.unpackNestedMap(document);
        this.documentCreatedAt = order.getCreatedAt();
        this.documentCustomerId = order.getCustomerId();
        this.documentProducts = order.getProducts();
    }

    @JsonIncludeProperties({ "createdAt", "customerId", "products" })
    public OrderWithoutId getDocument() {
        document.setCreatedAt(documentCreatedAt);
        document.setCustomerId(documentCustomerId);
        document.setProducts(documentProducts);
        return document;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderInsertMongoRequestDTO)) return false;
        if (!super.equals(o)) return false;
        OrderInsertMongoRequestDTO that = (OrderInsertMongoRequestDTO) o;
        return (
            Objects.equals(documentCreatedAt, that.documentCreatedAt) &&
            Objects.equals(documentCustomerId, that.documentCustomerId) &&
            Objects.equals(documentProducts, that.documentProducts)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), documentCreatedAt, documentCustomerId, documentProducts);
    }

    @Override
    public String toString() {
        return (
            "OrderInsertMongoRequestDTO{" +
            "documentCreatedAt=" +
            documentCreatedAt +
            ", documentCustomerId='" +
            documentCustomerId +
            '\'' +
            ", documentProducts=" +
            documentProducts +
            ", dataSource='" +
            dataSource +
            '\'' +
            ", database='" +
            database +
            '\'' +
            ", collection='" +
            collection +
            '\'' +
            "}"
        );
    }
}
