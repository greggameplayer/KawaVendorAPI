package com.kawa.service.dto.request.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kawa.domain.bean.ProductWithoutId;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class ProductInsertMongoRequestDTO {

    private String dataSource = "MainCluster";
    private String database = "kawavendorapi";
    private String collection = "products";
    private Date documentCreatedAt;
    private String documentName;
    private int documentStock;
    private double documentDetailsPrice;
    private String documentDetailsDescription;
    private String documentDetailsColor;

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

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
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
        if (o == null || getClass() != o.getClass()) return false;
        ProductInsertMongoRequestDTO that = (ProductInsertMongoRequestDTO) o;
        return (
            documentStock == that.documentStock &&
            Double.compare(that.documentDetailsPrice, documentDetailsPrice) == 0 &&
            Objects.equals(dataSource, that.dataSource) &&
            Objects.equals(database, that.database) &&
            Objects.equals(collection, that.collection) &&
            Objects.equals(documentCreatedAt, that.documentCreatedAt) &&
            Objects.equals(documentName, that.documentName) &&
            Objects.equals(documentDetailsDescription, that.documentDetailsDescription) &&
            Objects.equals(documentDetailsColor, that.documentDetailsColor)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            dataSource,
            database,
            collection,
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
            '}'
        );
    }
}
