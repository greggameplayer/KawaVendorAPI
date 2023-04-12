package com.kawa.service.dto.request.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.Objects;

public class ProductFindOneMongoRequestDTO {

    private String dataSource = "MainCluster";
    private String database = "kawavendorapi";
    private String collection = "products";
    private String filterId;

    @JsonProperty("filter")
    private void unpackNested(Map<String, Object> filter) {
        this.filterId = (String) filter.get("_id");
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

    public String getFilterId() {
        return filterId;
    }

    public void setFilterId(String filterId) {
        this.filterId = filterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductFindOneMongoRequestDTO that = (ProductFindOneMongoRequestDTO) o;
        return (
            Objects.equals(dataSource, that.dataSource) &&
            Objects.equals(database, that.database) &&
            Objects.equals(collection, that.collection) &&
            Objects.equals(filterId, that.filterId)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataSource, database, collection, filterId);
    }

    @Override
    public String toString() {
        return (
            "ProductFindOneMongoRequestDTO{" +
            "dataSource='" +
            dataSource +
            '\'' +
            ", database='" +
            database +
            '\'' +
            ", collection='" +
            collection +
            '\'' +
            ", filterId='" +
            filterId +
            '\'' +
            '}'
        );
    }
}
