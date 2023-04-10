package com.kawa.service.dto.request.mongo;

public class ProductFindAllMongoRequestDTO {

    private String dataSource = "MainCluster";
    private String database = "kawavendorapi";
    private String  collection = "products";

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
}
