package com.kawa.service.dto.request.mongo;

import java.util.Objects;

public abstract class FindAllMongoRequestDTO {

    protected String dataSource = "MainCluster";
    protected String database = "kawavendorapi";
    protected String collection;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindAllMongoRequestDTO that = (FindAllMongoRequestDTO) o;
        return (
            Objects.equals(dataSource, that.dataSource) &&
            Objects.equals(database, that.database) &&
            Objects.equals(collection, that.collection)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataSource, database, collection);
    }

    @Override
    public String toString() {
        return (
            "FindAllMongoRequestDTO{" +
            "dataSource='" +
            dataSource +
            '\'' +
            ", database='" +
            database +
            '\'' +
            ", collection='" +
            collection +
            '\'' +
            '}'
        );
    }
}
