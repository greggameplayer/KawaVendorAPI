package com.kawa.service.dto.request.mongo;

import com.kawa.domain.bean.Product;
import org.springframework.context.annotation.Bean;

public class ProductInsertMongoRequestDTO {
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

    @Bean
    public Document document(){
        return new Document();
    }


    public static class Document extends Product {
    }
}
